package com.udriving.drivingapi.controller;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.entity.dao.UDUserRepository;
import com.udriving.drivingapi.entity.weichat.WeiChatGetToken;
import com.udriving.drivingapi.entity.weichat.WeiChatResponse;
import com.udriving.drivingapi.security.jwt.JWTUserDetails;
import com.udriving.drivingapi.security.jwt.JWTUserDetailsFactory;
import com.udriving.drivingapi.security.jwt.JwtTokenUtil;
import com.udriving.drivingapi.util.HttpSynUtil;
import com.udriving.drivingapi.util.JacksonUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/18
 * 用户注册接口：
 * 1、API 注册，根据请求生成 UserId 并返回 Token
 * 2、微信注册。根据 code 获取 OpenId 并生成 UserId 返回 Token
 */
@Log4j2
@RestController
public class Reg {

    @Value("${weichat.mini.api.host}")
    private String weichatHost;

    @Value("${weichat.mini.appId}")
    private String weichatAppId;
    @Value("${weichat.mini.secret}")
    private String weiChatSecret;

    @Autowired
    private UDUserRepository udUserRepository;
    @Autowired
    JwtTokenUtil util;

    /**
     * 注册接口
     * 根据手机号处理，暂时不处理
     *
     * @param name
     */
    @RequestMapping(value = "/api/user/reg", method = RequestMethod.GET)
    public void reg(@RequestParam("phoneNum") String name) {


    }


    @RequestMapping(value = "/api/user/weichat/getToken", method = RequestMethod.GET)
    public String getToken(@RequestBody WeiChatGetToken weiChatGetToken) {
        //如果 weiChat 不为空，则先去
        if (StringUtils.isNoneEmpty(weiChatGetToken.getCode())) {
            try {
                URI uri = new URIBuilder(weichatHost).addParameter("appid", weichatAppId).addParameter("secret", weiChatSecret).addParameter("js_code", weiChatGetToken.getCode()).addParameter("grant_type", "authorization_code").build();
                String weiChatResponse = HttpSynUtil.get(uri);
                WeiChatResponse respons = JacksonUtil.json2Bean(weiChatResponse, WeiChatResponse.class);
                if (StringUtils.isEmpty(respons.getOpenid())) {
                    throw new BadCredentialsException("Request error id={}" + respons.getErrcode() + "\t msg:{}" + respons.getErrmsg());
                }
                UDUser userInfo = udUserRepository.findUserByOpenId(respons.getOpenid());
                //获取 UserInfo 以后暂时不需要验证密码
                String userId = java.util.UUID.randomUUID().toString();
                if (userInfo.getUserId() == null) {
                    log.info("微信用户注册：" + respons.getOpenid() + " 不存在，新创建 Useer 与 openId 绑定.");
                    userInfo = new UDUser();
                    userInfo.setPassword("");
                    userInfo.setDistinction("weichat reg");
                    userInfo.setNickname(weiChatGetToken.getCatName());
                    userInfo.setEnabled(true);
                    userInfo.setOpenId(respons.getOpenid());
                    userInfo.setUserId(userId);
                    udUserRepository.save(userInfo);
                }
                //生成 Token
                JWTUserDetails details = JWTUserDetailsFactory.create(userInfo, Instant.now());

                return util.generateAccessToken(details);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
