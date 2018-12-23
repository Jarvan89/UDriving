package com.udriving.drivingapi.security.NormalLogin;

import lombok.Data;

import java.time.Instant;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
@Data
public class UserDetail {
    public UserDetail() {

    }

    String userName;
    String userEmail;
    String userId;
    String token;
    String password;
    long id;
    int status;
    Instant lastPasswordResetDate;

}
