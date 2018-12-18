package com.udriving.drivingapi.security.config;

import com.udriving.drivingapi.entity.dao.UDUser;
import com.udriving.drivingapi.entity.dao.UDrole;
import com.udriving.drivingapi.security.entity.UserDetail;
import com.udriving.drivingapi.security.entity.UserRoleService;
import com.udriving.drivingapi.security.entity.UserRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 *
 * @remark: 配置用户权限认证
 * @explain 当用户登录时会进入此类的loadUserByUsername方法对用户进行验证，验证成功后会被保存在当前回话的principal对象中
 * 系统获取当前登录对象信息方法 WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * <p>
 * 异常信息：
 * UsernameNotFoundException     用户找不到
 * BadCredentialsException       坏的凭据
 * AccountExpiredException       账户过期
 * LockedException               账户锁定
 * DisabledException             账户不可用
 * CredentialsExpiredException   证书过期
 */
@Slf4j
@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户：" + username);
        //用户用户信息和用户角色
        UserRoleVO userRole = this.userRoleService.findUserAndRole(username);
        if (userRole.getUserId() == null) {
            //后台抛出的异常是：org.springframework.security.authentication.BadCredentialsException: Bad credentials  坏的凭证 如果要抛出UsernameNotFoundException 用户找不到异常则需要自定义重新它的异常
            log.info("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }

//        //获取用户信息
        UDUser userInfo = userRole.getUserInfo();
//        //获取用户拥有的角色 权限检查 todo
        List<UDrole> roleList = userRole.getRoles();

        Set<GrantedAuthority> grantedAuths = new HashSet<GrantedAuthority>();
        if (roleList!=null && roleList.size() > 0) {
            roleList.stream().forEach(role -> {
                grantedAuths.add(new SimpleGrantedAuthority(role.getAuthorizedSigns()));
            });
        }
        User userDetail = new User(userInfo.getUserId(), userInfo.getPassword(),
                grantedAuths);

        //不使用jwt 代码
        //return userDetail;


        //使用JWT 代码
        UserDetail user = DozerBeanMapperUtil.getObject(userInfo, UserDetail.class);
        user.setUserId(userInfo.getUserId());
        return JWTUserDetailsFactory.create(userDetail, user);
    }

}
