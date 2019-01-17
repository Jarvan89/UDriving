package com.udriving.drivingapi.security.util;

import com.udriving.drivingapi.security.exception.ErrorCodeEnum;
import com.udriving.drivingapi.util.JacksonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
public class ResultUtil {
    public static void writeJavaScript(HttpServletResponse httpServletResponse, ErrorCodeEnum codeEnum, String toString) {
        try {
            httpServletResponse.getWriter().append(toString).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJavaScript(HttpServletResponse httpServletResponse, ErrorCodeEnum codeEnum) {
        try {
            httpServletResponse.getWriter().append(codeEnum.getMessage()).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJavaScript(HttpServletResponse httpServletResponse, ErrorCodeEnum codeEnum,Object obj) {
        try {


            httpServletResponse.getWriter().append(JacksonUtil.toJSONString(obj)).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
