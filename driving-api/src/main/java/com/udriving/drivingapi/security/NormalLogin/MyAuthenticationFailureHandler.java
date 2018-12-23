package com.udriving.drivingapi.security.NormalLogin;

import com.udriving.drivingapi.security.exception.ErrorCodeEnum;
import com.udriving.drivingapi.security.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 * @remark:   用户登录系统失败后 需要做的业务操作
 * @explain   当用户登录系统失败后则会进入到此类并执行相关业务
 *
 */
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //用户登录时身份认证未通过
        System.out.println(e.getMessage());
        if (e instanceof BadCredentialsException){
            log.info("用户登录时：用户名或者密码错误.");
            ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.LOGIN_INCORRECT);
        }else{
            ResultUtil.writeJavaScript(httpServletResponse,ErrorCodeEnum.LOGIN_FAIL);
        }
    }
}

