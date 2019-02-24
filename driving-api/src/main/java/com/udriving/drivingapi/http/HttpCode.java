package com.udriving.drivingapi.http;

/**
 * http 返回的业务码 （2xxxx 开头）
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/24
 */
public enum HttpCode {
    SAVE_FAIL(20001, "保存失败"), ACTIVITY_NOT_FIND(20001, "活动未找到"), INTERIOR_ERROR(20003, "内部错误 "), SUCCEED(0, "");


    HttpCode(int code, String dec) {
    }

    ;
}
