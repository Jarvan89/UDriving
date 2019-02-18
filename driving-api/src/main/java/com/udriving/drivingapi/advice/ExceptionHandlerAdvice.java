package com.udriving.drivingapi.advice;

import com.udriving.drivingapi.entity.BaseErrorResult;

import com.udriving.drivingapi.exception.UDBaseException;
import lombok.extern.log4j.Log4j2;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/18
 */
@ControllerAdvice
@ResponseBody
@Log4j2
public class ExceptionHandlerAdvice implements ResponseBodyAdvice {

    private ThreadLocal<Object> modelHolder = new ThreadLocal<>();


    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }
        return null;
    }

    @ExceptionHandler(UDBaseException.class)
    public Object handleBaseException(UDBaseException e, HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }
        BaseErrorResult response = new BaseErrorResult(e.getErrorCode(), e.getErrorMsg());
        return response;
    }


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // ModelHolder 初始化
        modelHolder.set(webDataBinder.getTarget());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // ModelHolder 清理
        modelHolder.remove();
        return body;
    }
}