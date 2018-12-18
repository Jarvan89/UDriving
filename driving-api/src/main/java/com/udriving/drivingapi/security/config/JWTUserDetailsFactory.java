package com.udriving.drivingapi.security.config;

import com.udriving.drivingapi.security.entity.JWTUserDetails;
import com.udriving.drivingapi.security.entity.UserDetail;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 *
 * @remark: 负责创建JWTUserDetails 对象
 */
public final class JWTUserDetailsFactory {

    private JWTUserDetailsFactory() {

    }
    public static JWTUserDetails create(User user, Long userId, Instant date) {
        return new JWTUserDetails(userId, user.getUsername(), user.getPassword(), user.getAuthorities(), date);
    }

    public static JWTUserDetails create(User user, UserDetail userDetail) {
        return new JWTUserDetails(userDetail, user.getAuthorities());
    }
}

