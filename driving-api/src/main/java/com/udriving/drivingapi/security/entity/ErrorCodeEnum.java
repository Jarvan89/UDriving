package com.udriving.drivingapi.security.entity;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
public enum ErrorCodeEnum {NO_TOKEN(""), TOKEN_INVALID(""), LOGIN_INCORRECT(""), AUTHORITY(""), LOGIN_WITHOUT(""), LOGIN_FAIL(""), SUCCESS(""), FAIL("");


    String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }}
