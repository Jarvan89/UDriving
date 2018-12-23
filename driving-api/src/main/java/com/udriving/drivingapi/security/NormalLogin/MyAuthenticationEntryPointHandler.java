package com.udriving.drivingapi.security.NormalLogin;

import com.udriving.drivingapi.security.exception.ErrorCodeEnum;
import com.udriving.drivingapi.security.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 * @remark:   认证失败 需要做的业务操作
 * @explain   当检测到用户访问系统资源认证失败时则会进入到此类并执行相关业务
 *
 */
@Slf4j
@Component
public class MyAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        StringBuffer msg = new StringBuffer("请求访问: ");
        msg.append(httpServletRequest.getRequestURI()).append(" 接口， 因为登录超时，无法访问系统资源.");
        log.info(msg.toString());
        ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.LOGIN_WITHOUT,msg.toString());

      /*  boolean ajaxRequest = HttpUtils.isAjaxRequest(httpServletRequest);
        if (ajaxRequest){
            //如果是ajax请求 则返回自定义错误
            ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.LOGIN,map);
        }else {
            // 非ajax请求 则跳转到指定的403页面
            //此处省略...................
        }*/
    }
}
