package com.udriving.drivingapi.security.config;

import com.udriving.drivingapi.security.entity.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 * @remark:   自定义权限不足 需要做的业务操作
 * @explain   当用户登录系统后访问资源时因权限不足则会进入到此类并执行相关业务
 *
 */
@Slf4j
public class MyAccessDeniedHandler  implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        StringBuffer msg = new StringBuffer("请求: ");
        msg.append(httpServletRequest.getRequestURI()).append(" 权限不足，无法访问系统资源.");
        log.info(msg.toString());
        ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.AUTHORITY,msg.toString());


       /* boolean ajaxRequest = HttpUtils.isAjaxRequest(httpServletRequest);
        if (ajaxRequest){
            //如果是ajax请求 则返回403错
            ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.AUTHORITY,msg.toString());
        }else {
            // 非ajax请求 则跳转到指定的403页面
            //此处省略...................
        }*/
    }
}

