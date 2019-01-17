//package com.udriving.drivingapi.security.NormalLogin;
//
//import com.udriving.drivingapi.security.exception.ErrorCodeEnum;
//import com.udriving.drivingapi.security.util.ResultUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by IntelliJ IDEA
// * Coder : haiyang
// * Date:2018/12/16
// * @remark:   自定义 登录校验
// * @explain   调用登录接口时会进入到此类的attemptAuthentication方法 进行相关校验操作
// *
// */
//@Slf4j
//public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    AuthenticationManager authenticationManager;
//    public  MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler){
//        this.authenticationManager = authenticationManager;
//        this.setFilterProcessesUrl("/auth/v1/api/login");  //这句代码很重要，设置登陆的url 要和 WebSecurityConfig 配置类中的.loginProcessingUrl("/auth/v1/api/login/entry") 一致，如果不配置则无法执行 重写的attemptAuthentication 方法里面而是执行了父类UsernamePasswordAuthenticationFilter的attemptAuthentication（）
//        this.setAuthenticationManager(authenticationManager);   // AuthenticationManager 是必须的
//        this.setAuthenticationSuccessHandler(successHandler);  //设置自定义登陆成功后的业务处理
//        this.setAuthenticationFailureHandler(failureHandler); //设置自定义登陆失败后的业务处理
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        //校验验证码
//        String verifyCode = request.getParameter("verifyCode");
//        if(!checkValidateCode(verifyCode)){
//            ResultUtil.writeJavaScript(response, ErrorCodeEnum.FAIL,"验证码错误.");
//            return null;
//        }
//
//        //在这里根据 Code 获取
//        //设置获取 username 的属性字段   js传到后台接收数据的参数名
//        this.setUsernameParameter("username");
//        //设置获取password 的属性字段  js传到后台接收数据的参数名
//        this.setPasswordParameter("password");
//
//
//
////        return authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        "123",
////                        "123",
////                        new ArrayList<>()));
//
//
//        return super.attemptAuthentication(request, response);
//    }
//
//    /**
//     * 验证 验证码是否正确
//     * @param verifyCode
//     * @return
//     */
//    private boolean checkValidateCode(String verifyCode){
//        if(StringUtils.isBlank(verifyCode) || !verifyCode.trim().equals("1234")){
//            return false;
//        }
//        return true;
//    }
//}
//
