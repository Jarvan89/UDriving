package com.udriving.drivingapi.security.weichatlogin;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.security.NormalLogin.UserRoleVO;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/23
 */
@Service
public class WeiChatUserService {
    public UDUser findUserByOpenId(String openId) {
        return  new UDUser();
    }
}
