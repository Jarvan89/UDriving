package com.udriving.drivingapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/18
 */
@Data
@Log4j2
@AllArgsConstructor
public class UDBaseException extends Exception {
    int errorCode;
    String errorMsg;
}
