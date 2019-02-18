package com.udriving.drivingapi.advice;

import com.udriving.drivingapi.util.JacksonUtil;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/18
 */
@ControllerAdvice
@Log4j2
public class LogResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (log.isDebugEnabled()) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            String requestUriWithoutContextPath = servletRequest.getRequestURI().substring(servletRequest.getContextPath().length());
            log.debug("uri={} | responseBody={}", requestUriWithoutContextPath, JacksonUtil.toJSONString(body));
        }
        return body;
    }
}