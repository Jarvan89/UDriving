//package com.udriving.drivingapi.util;
//
//import com.udriving.drivingapi.entity.dao.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
///**
// * @Coder shihaiyang
// * @Date 2019-01-20 22:30
// */
//@Component
//public class InitialDataLoader implements
//        ApplicationListener<ContextRefreshedEvent> {
//
//    boolean alreadySetup = false;
//
//    @Autowired
//    private UDUserRepository userRepository;
//
//    @Autowired
//    private UDRoleRespository roleRepository;
//
//    @Autowired
//    private UDPrivilegeRespository privilegeRepository;
//
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//
//        if (alreadySetup)
//            return;
//        UDPrivilege readPrivilege
//                = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        UDPrivilege writePrivilege
//                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//        List<UDPrivilege> adminPrivileges = Arrays.asList(
//                readPrivilege, writePrivilege);
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//        UDRole adminRole = roleRepository.findByName("ROLE_ADMIN");
//        UDUser user = new UDUser();
//        user.setPassword("123456");
//        user.setRoles(Arrays.asList(adminRole));
//        user.setEnabled(true);
//        userRepository.save(user);
//
//        alreadySetup = true;
//    }
//
//    @Transactional
//    UDPrivilege createPrivilegeIfNotFound(String name) {
//
//        UDPrivilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new UDPrivilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    UDRole createRoleIfNotFound(
//            String name, Collection<UDPrivilege> privileges) {
//
//
//        UDRole role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new UDRole(name);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//        return role;
//    }
//}