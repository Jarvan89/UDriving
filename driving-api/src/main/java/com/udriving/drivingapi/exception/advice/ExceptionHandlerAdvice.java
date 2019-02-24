package com.udriving.drivingapi.exception.advice;

import com.udriving.drivingapi.entity.BaseErrorResult;

import com.udriving.drivingapi.exception.UDBaseException;
import lombok.extern.log4j.Log4j2;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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
    public void MethodArgumentNotValidException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        MethodArgumentNotValidException c = (MethodArgumentNotValidException) ex;
        List<ObjectError> errors = c.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer();
        errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
        pouplateExceptionResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, errorMsg.toString());
    }

    private void pouplateExceptionResponse(HttpServletResponse response, HttpStatus errorCode, String errorMessage) {
        try {
            response.sendError(errorCode.value(), errorMessage);
        } catch (IOException e) {
            log.error("failed to populate response error", e);
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleArgumentException(MethodArgumentNotValidException e) {

        log.info("{}\t{}\t{}", e.getMessage(), e.getParameter(), e.getBindingResult());
        return e.getMessage();
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