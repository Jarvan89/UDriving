package com.udriving.drivingapi.controller.response;

import lombok.Data;

/**
 * 接口返回数据结构
 */
@Data
public class Response implements ResponseConstant {
    /**
     * 接口响应状态
     */
    byte code = SUCCEED;
    /**
     *
     */
    String message;
    /**
     *
     */
    Object data;
}
