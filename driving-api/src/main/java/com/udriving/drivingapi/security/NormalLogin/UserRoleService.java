package com.udriving.drivingapi.security.NormalLogin;

import com.udriving.drivingapi.entity.dao.UDUser;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
@Service
public class UserRoleService {
    public UserRoleVO findUserAndRole(String username) {
        UserRoleVO userRoleVO = new UserRoleVO();
        UDUser user = new UDUser();
        user.setUserId("123");
        user.setPassword("$2a$10$oSm4vUl6vkQnADxqRqaFyeU.H1YKQEiJsvLICxH.tc5mZd8DD2s5y");
        userRoleVO.setUser(user);
        return userRoleVO ;
    }
}
