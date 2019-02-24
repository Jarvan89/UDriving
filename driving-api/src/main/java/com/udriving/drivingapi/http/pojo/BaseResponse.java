package com.udriving.drivingapi.http.pojo;

import com.udriving.drivingapi.http.HttpCode;
import com.udriving.drivingapi.http.controller.response.ResponseConstant;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 接口返回数据结构
 */
@Data
@RequiredArgsConstructor
public class BaseResponse implements ResponseConstant {
    public BaseResponse(Object data) {
        this.data = data;
    }

    public BaseResponse(HttpCode data) {
        this.data = data;
    }

    /**
     * 接口响应状态
     */
    short code = SUCCEED;
    /**
     * 接口响应状态描述
     */
    String message;
    /**
     * 接口响应附带数据
     */

    Object data;
}
