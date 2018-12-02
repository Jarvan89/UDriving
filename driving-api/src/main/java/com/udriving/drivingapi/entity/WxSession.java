package com.udriving.drivingapi.entity;


import lombok.Data;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/11/28
 */
@Data
public class WxSession {

    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;

    public WxSession() {

    }
}