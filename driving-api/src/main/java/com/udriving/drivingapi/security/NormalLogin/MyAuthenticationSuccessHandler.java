package com.udriving.drivingapi.security.NormalLogin;

import com.udriving.drivingapi.security.jwt.JWTUserDetails;
import com.udriving.drivingapi.security.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 * @remark:   用户登录系统成功后 需要做的业务操作
 * @explain   当用户登录系统成功后则会进入到此类并执行相关业务
 *
 */
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获得授权后可得到用户信息(非jwt 方式)
        //User userDetails =  (User) authentication.getPrincipal();

        //获得授权后可得到用户信息(jwt 方式)
        JWTUserDetails userDetails =  (JWTUserDetails) authentication.getPrincipal();
        //将身份 存储到SecurityContext里
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        httpServletRequest.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        StringBuffer msg = new StringBuffer("用户：");
        msg.append(userDetails.getUsername()).append(" 成功登录系统.");
        log.info(msg.toString());
        //使用jwt生成token 用于权限效验
        String token = jwtTokenUtil.generateAccessToken(userDetails);
        UserDetail user = userDetails.getUserInfo();
        user.setToken(token);
        //将登录人信息放在redis中
//        this.saveTokenToRedis(user.getAccountId(),token,JSON.toJSONString(user));
        String access_token = tokenHead+token;
        String refresh_token = tokenHead+jwtTokenUtil.refreshToken(token);
        Map<String,String> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("refresh_token", refresh_token);
        map.put("userId",user.getUserId());
        map.put("userName",user.getUserName());
        map.put("email",user.getUserEmail());
        map.put("msage",msg.toString());
//        RestfulVo restfulVo = ResultUtil.resultInfo(ErrorCodeEnum.SUCCESS,map);
//        ResultUtil.writeJavaScript(httpServletResponse,restfulVo);
    }

//    /**
//     * 将用户token 和用户信息 保存到redis中
//     * @param userId  用户id
//     * @param token   用户token
//     * @param value   用户信息
//     */
//    private void saveTokenToRedis(Long userId,String token,String value){
//        String userKey =  RedisKeys.USER_KEY;
//        redisUtil.hset(userKey,token,value,expiration);
//    }
}
