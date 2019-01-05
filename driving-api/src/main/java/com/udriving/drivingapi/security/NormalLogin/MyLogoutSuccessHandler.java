package com.udriving.drivingapi.security.NormalLogin;

import com.udriving.drivingapi.security.exception.ErrorCodeEnum;
import com.udriving.drivingapi.security.util.ResultUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/16
 * @remark:   用户退出系统成功后 需要做的业务操作
 * @explain   当用户退出系统成功后则会进入到此类并执行相关业务
 *
 */
@Log4j2
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

//    @Autowired
//    private RedisUtil redisUtil;
//    @Autowired
//    private UserUtils userUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //根据token清空redis
//        String userKey =  RedisKeys.USER_KEY;
//        String token = userUtils.getUserToken(httpServletRequest);
//        redisUtil.hdel(userKey,token);
        SecurityContextHolder.clearContext();  //清空上下文
        httpServletRequest.getSession().removeAttribute("SPRING_SECURITY_CONTEXT"); // 从session中移除
        //退出信息插入日志记录表中
        ResultUtil.writeJavaScript(httpServletResponse, ErrorCodeEnum.SUCCESS,"退出系统成功.");
    }
}