package com.udriving.drivingapi.controller;

import com.udriving.drivingapi.entity.WxSession;
import com.udriving.drivingapi.security.jwt.JwtTokenUtil;
import com.udriving.drivingapi.util.HttpAsyncUtil;
import com.udriving.drivingapi.util.JacksonUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    private JwtTokenUtil jwtTokenUtil;
    /**
     * 注册接口
     * 根据手机号处理，暂时不处理
     * @param name
     */
    @RequestMapping(value = "/api/user/reg", method = RequestMethod.GET)
    public void reg(@RequestParam("phoneNum") String name) {


    }


    @RequestMapping(value = "/api/user/getToken", method = RequestMethod.GET)
    public void getToken(@RequestParam("weiChatCode") String code,  HttpServletResponse response) {
        //如果 weiChat 不为空，则先去
        if (StringUtils.isNoneEmpty(code)) {
            try {
                URI uri = new URIBuilder(weichatHost).addParameter("appid", weichatAppId).addParameter("secret", weiChatSecret).addParameter("js_code", code).addParameter("grant_type", "authorization_code").build();
                final HttpGet httpGet = new HttpGet(uri);
                HttpAsyncUtil.getInstance().sendHttpGet(httpGet, new FutureCallback<HttpResponse>() {
                    @Override
                    public void completed(HttpResponse rp) {

                        try {
                            String bd = EntityUtils.toString(rp.getEntity(), "UTF-8");
                            WxSession ws = JacksonUtil.json2Bean(bd, WxSession.class);
                            //拿到微信信息以后生成 User 入库。
                            //入库成功返回 token
//                            JWTUserDetails
//                            jwtTokenUtil.generateAccessToken(user);
                            response.getWriter().write(ws.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Exception e) {
                        //失败返回
                        try {
                            response.getWriter().write(e.toString());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void cancelled() {
                        log.info("cancelled");
                    }
                });
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }


    }
}
