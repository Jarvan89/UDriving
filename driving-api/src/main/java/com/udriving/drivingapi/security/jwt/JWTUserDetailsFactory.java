package com.udriving.drivingapi.security.jwt;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.entity.dao.UDRole;
import com.udriving.drivingapi.security.NormalLogin.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.Collection;
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
        return new JWTUserDetails(user.getId(), user.getNickname(), user.getPassword(), mapToGrantedAuthorities(user.getRoles()), date);
    }

    public static JWTUserDetails create(UDUser user, UserDetail userDetail) {
        return new JWTUserDetails(userDetail, mapToGrantedAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Collection<UDRole> authorities) {

        return authorities.stream().map(UDRole -> new SimpleGrantedAuthority(UDRole.getName())).collect(Collectors.toList());

    }


}

