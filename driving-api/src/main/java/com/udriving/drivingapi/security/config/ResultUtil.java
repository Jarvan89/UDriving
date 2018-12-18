package com.udriving.drivingapi.security.config;

import com.udriving.drivingapi.security.entity.ErrorCodeEnum;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
public class ResultUtil {
    public static void writeJavaScript(HttpServletResponse httpServletResponse, ErrorCodeEnum tokenInvalid, String toString) {
    }

    public static void writeJavaScript(HttpServletResponse httpServletResponse, ErrorCodeEnum loginIncorrect) {
    }
}
