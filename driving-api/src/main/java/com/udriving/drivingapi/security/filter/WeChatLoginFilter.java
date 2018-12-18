package com.udriving.drivingapi.security.filter;

import com.udriving.drivingapi.entity.WxSession;
import com.udriving.drivingapi.util.HttpAsyncUtil;
import com.udriving.drivingapi.util.JacksonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2018/12/3
 */
public class WeChatLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("---------4--------");
        String code = servletRequest.getParameter("code");
//        appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code

        try {
            URI uri = new URIBuilder("https://api.weixin.qq.com/sns/jscode2session").addParameter("appid", "appid").addParameter("secret", "secret").addParameter("js_code", code).addParameter("grant_type", "authorization_code").build();
            final HttpGet httpGet = new HttpGet(uri);
            HttpAsyncUtil.getInstance().sendHttpGet(httpGet, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse rp) {
                    /* 继续调用 Filter 链 */
                    try {
                        String bd = EntityUtils.toString(rp.getEntity(), "UTF-8");
                        WxSession ws = JacksonUtil.json2Bean(bd, WxSession.class);
                        //do file error code
                        if (ws.getErrcode().equals("0")) {
                            filterChain.doFilter(servletRequest, servletResponse);
                        } else {
                            System.out.println(ws.getErrmsg());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void failed(Exception e) {
                    //失败返回
                }

                @Override
                public void cancelled() {

                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
//        httpGet.setParams();


    }
}


