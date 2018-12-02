package com.udriving.drivingapi.security;
import static java.util.Collections.emptyList;

import com.udriving.drivingapi.entity.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/11/28
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws  UsernameNotFoundException {
        UserInfo user = new UserInfo();
        return new User(user.getUsername(), user.getPassword(), emptyList());
    }
}