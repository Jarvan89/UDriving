package com.udriving.drivingapi.security.jwt;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.entity.dao.UDrole;
import com.udriving.drivingapi.security.NormalLogin.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 *
 * @remark: 负责创建JWTUserDetails 对象
 */
public final class JWTUserDetailsFactory {

    private JWTUserDetailsFactory() {

    }

    public static JWTUserDetails create(UDUser user, Instant date) {
        return new JWTUserDetails(user.getId(), user.getNickname(), user.getPassword(), mapToGrantedAuthorities(user.getRole()), date);
    }

    public static JWTUserDetails create(UDUser user, UserDetail userDetail) {
        return new JWTUserDetails(userDetail, mapToGrantedAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<UDrole> authorities) {

        return authorities.stream().map(uDrole -> new SimpleGrantedAuthority(uDrole.getRole())).collect(Collectors.toList());

    }


}

