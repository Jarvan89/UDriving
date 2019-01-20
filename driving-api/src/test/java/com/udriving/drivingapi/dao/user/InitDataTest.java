package com.udriving.drivingapi.dao.user;

import com.udriving.drivingapi.entity.dao.*;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @Coder shihaiyang
 * @Date 2019-01-20 22:58
 */

// 获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
// 让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
@Log4j2
public class InitDataTest {


    @Autowired
    private UDUserRepository userRepository;

    @Autowired
    private UDRoleRespository roleRepository;

    @Autowired
    private UDPrivilegeRespository privilegeRepository;

    @Test
    public void initData() {


        UDPrivilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        UDPrivilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<UDPrivilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
        UDRole adminRole = roleRepository.findByName("ROLE_ADMIN");
        UDUser user = new UDUser();
        user.setPassword("123456");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

    }

    @Transactional
    UDPrivilege createPrivilegeIfNotFound(String name) {

        UDPrivilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new UDPrivilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    UDRole createRoleIfNotFound(
            String name, Collection<UDPrivilege> privileges) {


        UDRole role = roleRepository.findByName(name);
        if (role == null) {
            role = new UDRole(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
