package com.udriving.drivingapi.controller.response;

/**
 * 接口返回体常量
 */
public interface ResponseConstant {
    /**
     * 失败-存储失败
     */
    short SAVE_FAIL = -203;
    /**
     * 失败-活动未找到
     */
    short ACTIVITY_NOT_FIND = -202;
    /**
     * 失败-通用原因未明确的失败均用此值
     */
    short ERROR = -201;
    /**
     * 成功
     */
    short SUCCEED = 0;
}
