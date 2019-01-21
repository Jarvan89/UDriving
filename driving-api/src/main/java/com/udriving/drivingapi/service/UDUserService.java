package com.udriving.drivingapi.service;

import com.udriving.drivingapi.entity.dao.UDRole;
import com.udriving.drivingapi.entity.dao.UDRoleRespository;
import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.entity.dao.UDUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务，增删改查
 *
 * @Coder shihaiyang
 * @Date 2019-01-21 11:03
 */
@Service
@Log4j2
public class UDUserService {

    @Autowired
    UDUserRepository udUserRepository;
    @Autowired
    UDRoleRespository udRoleRespository;

    public UDUser findUserByOpenId(String openId) {

        return udUserRepository.findUserByOpenId(openId);
    }


    /**
     * 注册用户默认 USER 权限
     *
     * @param user
     */
    public void regUser(UDUser user) {
        UDRole role = getRole("USER");
        if (role == null)
            role = new UDRole();
        role.setName("USER");
        udRoleRespository.save(role);
        log.info("UDRole :{} is not in databases and save", role.getName());
        List roles = new ArrayList<UDRole>();
        roles.add(role);
        user.setRoles(roles);
        udUserRepository.save(user);
    }

    public void updateUser(UDUser user) {

    }

    public UDRole getRole(String role) {
        return udRoleRespository.findByName(role);
    }
}
