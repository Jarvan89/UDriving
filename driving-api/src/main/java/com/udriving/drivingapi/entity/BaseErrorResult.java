package com.udriving.drivingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA
 * 基本异常返回对象
 * Coder : haiyang
 * Date:2019/2/18
 */
@Data
@ToString
@AllArgsConstructor
public class BaseErrorResult {
    int errorCode;
    String errorMsg;
}


