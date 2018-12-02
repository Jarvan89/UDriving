package com.udriving.drivingapi.entity;


import lombok.Data;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/11/28
 */
@Data
public class UserInfo {

    private String id;
    private String username;
    private String password;

    public UserInfo() {
        this.setId("testId");
        this.setUsername("testUsername");
        this.setPassword("testPassword");
    }
}