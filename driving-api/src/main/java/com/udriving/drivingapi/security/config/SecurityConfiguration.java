package com.udriving.drivingapi.security.config;

import com.udriving.drivingapi.security.filter.JwtAuthenticationFilter;
import com.udriving.drivingapi.security.filter.JwtLoginFilter;
import com.udriving.drivingapi.security.filter.WeChatLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/11/28
 */


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义 默认
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/user/login", "/login", "/oauth/authorize").permitAll()
                .anyRequest().authenticated()
                .and()
                .requestMatchers().antMatchers("/user/login", "/login", "/oauth/authorize")
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))//登录过滤器
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));//自定义过滤器
        // 在 JwtLoginFilter 前添加 WeChatLoginFilter
        HttpSecurity httpSecurity = http.addFilterBefore(new WeChatLoginFilter(), JwtLoginFilter.class);


    }

}