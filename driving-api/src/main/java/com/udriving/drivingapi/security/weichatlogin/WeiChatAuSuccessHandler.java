package com.udriving.drivingapi.security.weichatlogin;

import com.udriving.drivingapi.security.jwt.JWTUserDetails;
import com.udriving.drivingapi.security.NormalLogin.UserDetail;
import com.udriving.drivingapi.security.exception.ErrorCodeEnum;
import com.udriving.drivingapi.security.jwt.JwtTokenUtil;
import com.udriving.drivingapi.security.util.ResultUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/22
 */
@Log4j2
public class WeiChatAuSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    //    @Autowired
//    private RedisUtil redisUtil;
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.expiration}")
    private Long expiration;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //如果成功了返回 token


        //获得授权后可得到用户信息(非jwt 方式)
        //User userDetails =  (User) authentication.getPrincipal();

        //获得授权后可得到用户信息(jwt 方式)
        JWTUserDetails userDetails = (JWTUserDetails) authentication.getPrincipal();
        //将身份 存储到SecurityContext里
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        StringBuffer msg = new StringBuffer("用户：");
        msg.append(userDetails.getUsername()).append(" 成功登录系统.");
        log.info(msg.toString());
        //使用jwt生成token 用于权限效验
        String token = jwtTokenUtil.generateAccessToken(userDetails);
        UserDetail user = userDetails.getUserInfo();
        user.setToken(token);
        //将登录人信息放在redis中
//        this.saveTokenToRedis(user.getAccountId(),token,JSON.toJSONString(user));
        String access_token = tokenHead + token;
        String refresh_token = tokenHead + jwtTokenUtil.refreshToken(token);
        Map<String, String> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("refresh_token", refresh_token);
        map.put("userId", user.getUserId());
        map.put("userName", user.getUserName());
        map.put("email", user.getUserEmail());
        map.put("msage", msg.toString());
//        RestfulVo restfulVo = ResultUtil.resultInfo(ErrorCodeEnum.SUCCESS,map);
        ResultUtil.writeJavaScript(response,ErrorCodeEnum.SUCCESS,map);


        System.out.println("Success");
    }
}
