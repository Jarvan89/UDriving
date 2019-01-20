package com.udriving.drivingapi.security.NormalLogin;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.entity.dao.UDRole;
import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 */
@Data
public class UserRoleVO {
    UDUser user;
    List<UDRole> UDRoles;
    public String  getUserId() {
        return user.getUserId();
    }

    public UDUser getUserInfo() {
        return  user;
    }

    public List<UDRole> getRoles() {
        return UDRoles;
    }
}
