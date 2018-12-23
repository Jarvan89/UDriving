package com.udriving.drivingapi.controller.response;

/**
 * 接口返回体常量
 */
public interface ResponseConstant {
    /**
     * 失败-活动未找到
     */
    byte SAVE_FAIL = -3;
    /**
     * 失败-活动未找到
     */
    byte ACTIVITY_NOT_FIND = -2;
    /**
     * 失败-通用原因未明确的失败均用此值
     */
    byte ERROR = -1;
    /**
     * 成功
     */
    byte SUCCEED = 0;
}
