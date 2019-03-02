package com.udriving.drivingapi.http.pojo;

import com.udriving.drivingapi.http.HttpCode;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 接口返回数据结构
 */
@Data
@RequiredArgsConstructor
public class BaseResponse {
    public BaseResponse(Object data) {
        this.data = data;
    }

    /**
     * 接口响应状态
     */
    private int code;
    /**
     * 接口响应状态描述
     */
    private String message;
    /**
     * 接口响应附带数据
     */
    private Object data;

    public BaseResponse(HttpCode httpCode) {
        this.code = httpCode.ordinal();
    }
}
