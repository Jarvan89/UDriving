package com.udriving.drivingapi.security.config;


import com.udriving.drivingapi.security.weichatlogin.WeiChatFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/11/28
 */

//@Configuration
////@EnableWebSecurity  //启动web安全性
////@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启方法级的权限注解  性设置后控制器层的方法前的@PreAuthorize("hasRole('admin')") 注解才能起效
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                // 由于使用的是JWT，我们这里不需要csrf
//                .csrf().disable()
//
//                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
//
////                .authorizeRequests()
//                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//
//                // 允许对于网站静态资源的无授权访问
////                .antMatchers(
////                        HttpMethod.GET,
////                        "/",
////                        "/*.html",
////                        "/favicon.ico",
////                        "/**/*.html",
////                        "/**/*.css",
////                        "/**/*.js"
////                ).permitAll()
//                // 对于获取token的rest api要允许匿名访问
////                .antMatchers("/api/user/getToken").permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
////                .anyRequest().authenticated();
//// 添加JWT filter
////        httpSecurity
////                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//
//        // 禁用缓存
////        httpSecurity.headers().cacheControl();
//    }
//
//
//    @Bean
//    public WeiChatAuthenticationFilter_new authenticationTokenFilterBean() throws Exception {
//        return new WeiChatAuthenticationFilter_new();
//    }
//
//}WeiChatAuthenticationFilter_new
