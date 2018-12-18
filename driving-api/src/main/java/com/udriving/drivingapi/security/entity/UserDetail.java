package com.udriving.drivingapi.security.entity;

import lombok.Data;

import java.time.Instant;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
@Data
public class UserDetail {
    String userName;
    String userEmail;
    String userId;
    String token;

    public String getUserAccount() {
        return  "1234";
    }

    public Long getAccountId() {return  null;
    }

    public String getUserPwd() {return  null;
    }

    public int getStatus() {return  1;
    }

    public Instant getLastPasswordResetDate() {return  null;
    }

    public void setToken(String token) {
    }



}
