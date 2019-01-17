//package com.udriving.drivingapi.security.weichatlogin;
//
//import com.udriving.drivingapi.entity.dao.UDUser;
//import com.udriving.drivingapi.entity.weichat.WeiChatResponse;
//import com.udriving.drivingapi.security.jwt.JWTUserDetails;
//import com.udriving.drivingapi.util.HttpSynUtil;
//import com.udriving.drivingapi.util.JacksonUtil;
//import lombok.extern.log4j.Log4j2;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.client.utils.URIBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by IntelliJ IDEA
// * Coder : haiyang
// * 过滤器拦截请求，过滤有效请求并交给 Provider 处理
// * Date:2018/12/20
// */
//@Log4j2
//public class WeiChatAuthenticationFilter_new extends OncePerRequestFilter {
//    @Value("${weichat.mini.api.host}")
//    private String weichatHost;
//    @Value("${weichat.mini.appId}")
//    private String weichatAppId;
//    @Value("${weichat.mini.secret}")
//    private String weiChatSecret;
//    private boolean postOnly = true;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if (postOnly && !httpServletRequest.getMethod().equals("POST")) {
//            throw new AuthenticationServiceException(
//                    "Authentication method not supported: " + httpServletRequest.getMethod());
//        }
//
//        //读取 json 数据
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;
//        try {
//            InputStream inputStream = httpServletRequest.getInputStream();
//            if (inputStream != null) {
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                char[] charBuffer = new char[128];
//                int bytesRead = -1;
//                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                    stringBuilder.append(charBuffer, 0, bytesRead);
//                }
//            } else {
//                stringBuilder.append("");
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        String body = stringBuilder.toString();
//
//
//        log.info("request body {}", body);
//        //根据微信 code 获取 openId ，根据 openId 检查是否有这个用户，如果没有新建用户 返回 token
////        authentication.setAuthenticated(false);
//        WeiChatAuthenticationToken authRequest = null;
//        try {
//            String weiChatCode = JacksonUtil.getMapper().readTree(body).get("code").textValue();
//
//            if (weiChatCode == null) {
//                throw new BadCredentialsException("Code is requisite");
//            }
//            URI uri = new URIBuilder(weichatHost).addParameter("appid", weichatAppId).addParameter("secret", weiChatSecret).addParameter("js_code", weiChatCode).addParameter("grant_type", "authorization_code").build();
//            String weiChatResponse = HttpSynUtil.get(uri);
//            //通过 OpenId 获取用户数据 {"session_key":"M1NoxN0MIRHrJzmtVGYV5w==","openid":"osfk65NOeuYDT3EU7nn7dmK0Xij8"}
//            //todo
//
//            log.info("wei chat response = {}", weiChatResponse);
//
//            WeiChatResponse weChatBean = null;
//            try {
//
//                weChatBean = JacksonUtil.json2Bean(weiChatResponse, WeiChatResponse.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//            if (StringUtils.isEmpty(weChatBean.getOpenid())) {
//                throw new BadCredentialsException("Request error id={}" + weChatBean.getErrcode() + "\t msg:{}" + weChatBean.getErrmsg());
//            }
//            String openId = JacksonUtil.getMapper().readTree(weiChatResponse).get("openid").textValue();
//            log.info("微信登陆：" + openId);
////            //用户用户信息和用户角色
////            UDUser userInfo = this.weiChatUserService.findUserByOpenId(openId);
////            if (userInfo.getUserId() == null) {
////                //后台抛出的异常是：org.springframework.security.authentication.BadCredentialsException: Bad credentials  坏的凭证 如果要抛出UsernameNotFoundException 用户找不到异常则需要自定义重新它的异常
////                log.info("登录用户：" + openId + " 不存在，新创建 Useer 与 openId 绑定.");
////                userInfo = new UDUser();
////                userInfo.setPassword("1234");
////                userInfo.setId(12);
////                userInfo.setDistinction("12");
////                userInfo.setNickname("haha");
////                userInfo.setStatus(1);
//////                throw new UsernameNotFoundException("登录用户：" + openId + " 不存在");
////            }
//            //使用JWT 代码
////            JWTUserDetails userDetail = DozerBeanMapperUtil.getObject(userInfo, JWTUserDetails.class);
//            List list = new ArrayList<String>();
//            ((ArrayList) list).add("ADMIN");
//            JWTUserDetails userDetail = new JWTUserDetails(1l, "userName", "password", list, Instant.now());
////            authRequest = new WeiChatAuthenticationToken(userDetail, weiChatCode);
//////            authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
////
////            SecurityContextHolder.getContext().setAuthentication(authRequest);
//
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            throw new IllegalArgumentException(e.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
