package com.udriving.drivingapi.security.exception;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
public enum ErrorCodeEnum {NO_TOKEN("no token"), TOKEN_INVALID("token invalid"), LOGIN_INCORRECT("login incorrect"), AUTHORITY("authority"), LOGIN_WITHOUT("login without"), LOGIN_FAIL("login fail"), SUCCESS("success"), FAIL("fail");


    String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }}
