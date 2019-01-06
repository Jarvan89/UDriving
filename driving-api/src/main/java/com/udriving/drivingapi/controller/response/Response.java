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
    short code = SUCCEED;
    /**
     *接口响应状态描述
     */
    String message;
    /**
     *接口响应附带数据
     */
    Object data;
}
