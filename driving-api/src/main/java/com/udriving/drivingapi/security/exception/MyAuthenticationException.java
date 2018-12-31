package com.udriving.drivingapi.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
public class MyAuthenticationException extends AuthenticationException {

    public MyAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyAuthenticationException(String msg) {
        super(msg);
    }

    /**
     * 加入错误状态值
     * @param exceptionEnum
     */
    public MyAuthenticationException(ErrorCodeEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
    }

}

