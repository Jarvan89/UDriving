//package com.udriving.drivingapi.security.weichatlogin;
//
//import com.udriving.drivingapi.entity.dao.UDUser;
//import com.udriving.drivingapi.security.NormalLogin.UserDetail;
//import com.udriving.drivingapi.security.jwt.JWTUserDetails;
//import com.udriving.drivingapi.security.util.DozerBeanMapperUtil;
//import com.udriving.drivingapi.security.jwt.JwtTokenUtil;
//import lombok.extern.log4j.Log4j2;
//import org.apache.http.client.utils.URIBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
///**
// * Created by IntelliJ IDEA
// * Coder : haiyang
// * Date:2018/12/19
// */
//@Log4j2
//public class WeiChatAuthenticationProvider implements AuthenticationProvider {
//
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        System.out.println("Authentication");
//
//        return authentication;
//
//    }
//
//    @Override
//    public boolean supports(Class<? extends Object> authentication) {
//        System.out.println("supports");
//        return (WeiChatAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//
//}