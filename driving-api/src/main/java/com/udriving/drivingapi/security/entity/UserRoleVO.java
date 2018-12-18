package com.udriving.drivingapi.security.entity;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.entity.dao.UDrole;
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
    List<UDrole> uDroles;
    public String  getUserId() {
        return user.getUserId();
    }

    public UDUser getUserInfo() {
        return  user;
    }

    public List<UDrole> getRoles() {
        return  uDroles;
    }
}
