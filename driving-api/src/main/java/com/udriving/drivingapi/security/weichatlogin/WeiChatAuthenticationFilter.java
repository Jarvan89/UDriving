package com.udriving.drivingapi.security.weichatlogin;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.security.jwt.JWTUserDetails;

import com.udriving.drivingapi.security.util.DozerBeanMapperUtil;
import com.udriving.drivingapi.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * 过滤器拦截请求，过滤有效请求并交给 Provider 处理
 * Date:2018/12/20
 */
@Slf4j
public class WeiChatAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;
    @Autowired
    private WeiChatUserService weiChatUserService;
    AuthenticationManager authenticationManager;

    public WeiChatAuthenticationFilter(AuthenticationManager authenticationManager, WeiChatAuSuccessHandler successHandler, WeiChatFailHandler failureHandler) {
        this.setFilterProcessesUrl("/auth/v1/api/login/entry");//这句代码很重要，设置登陆的url 要和 WebSecurityConfig 配置类中的.loginProcessingUrl("/auth/v1/api/login/entry") 一致，如果不配置则无法执行 重写的attemptAuthentication 方法里面而是执行了父类UsernamePasswordAuthenticationFilter的attemptAuthentication（）
        this.authenticationManager = authenticationManager;
        this.setAuthenticationManager(authenticationManager);   // AuthenticationManager 是必须的
        this.setAuthenticationSuccessHandler(successHandler);  //设置自定义登陆成功后的业务处理
        this.setAuthenticationFailureHandler(failureHandler); //设置自定义登陆失败后的业务处理
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String weiChatCode = request.getParameter("code").toString();
        if (weiChatCode == null) {

            throw new BadCredentialsException("Code is requisite");
        }

        //根据微信 code 获取 openId ，根据 openId 检查是否有这个用户，如果没有新建用户 返回 token
//        authentication.setAuthenticated(false);
        String code = "";
        WeiChatAuthenticationToken authRequest;
        try {
            URI uri = new URIBuilder("https://api.weixin.qq.com/sns/jscode2session").addParameter("appid", "appid").addParameter("secret", "secret").addParameter("js_code", code).addParameter("grant_type", "authorization_code").build();
            String weiChatResponse = HttpClientUtil.get(uri);
            System.out.println(weiChatResponse);
            //通过 OpenId 获取用户数据
            //todo
            String openId = "";
            log.info("微信登陆：" + openId);
            //用户用户信息和用户角色
            UDUser userInfo = this.weiChatUserService.findUserByOpenId(openId);
            if (userInfo.getUserId() == null) {
                //后台抛出的异常是：org.springframework.security.authentication.BadCredentialsException: Bad credentials  坏的凭证 如果要抛出UsernameNotFoundException 用户找不到异常则需要自定义重新它的异常
                log.info("登录用户：" + openId + " 不存在，新创建 Useer 与 openId 绑定.");
                throw new UsernameNotFoundException("登录用户：" + openId + " 不存在");
            }
            //使用JWT 代码
            JWTUserDetails userDetail = DozerBeanMapperUtil.getObject(userInfo, JWTUserDetails.class);
            authRequest = new WeiChatAuthenticationToken(userDetail, weiChatCode);
            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
        return authRequest;
    }
}
