package com.udriving.boot;

import com.alibaba.fastjson.JSON;
import com.google.inject.Inject;
import com.udriving.api.execute.base.BaseExecute;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 请求分发实现
 * 所有请求的第一站均到这里，然后根据url或表单中的方法名(functionId)进行选择进一步处理类，然后在回到这里构造输出
 */
public class RequestDistribute extends ChannelInboundHandlerAdapter {
    /**
     * 方法名
     */
    private String functionId;
    /**
     * 参数
     */
    private String parameters;
    /**
     * 请求类型
     */
    private HttpMethod requestMethod;
    /**
     * Http请求
     */
    private HttpRequest httpRequest;
    /**
     * 执行器Map，key为对外命名的functionId，Value为可处理functionId的BaseExecute的实现类
     */
    @Inject
    private Map<String, BaseExecute> executeMap;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("executeMap:" + JSON.toJSONString(executeMap));
        //获取请求方式标识
        if (msg instanceof HttpRequest) {
            httpRequest = (HttpRequest) msg;
            requestMethod = httpRequest.method();
        }

        //若是Get请求
        if (requestMethod.equals(HttpMethod.GET)) {
            //则从请求体中获取参数
            if (msg instanceof HttpRequest) {
                //get请求从url中获取functionId
                functionId = httpRequest.uri().substring(httpRequest.uri().indexOf("/") + 1, httpRequest.uri().indexOf("?") != -1 ? httpRequest.uri().indexOf("?") : httpRequest.uri().length());
                //get请求从url中获取parameters
                if (httpRequest.uri().indexOf("?") != -1) {
                    try {
                        parameters = URLDecoder.decode(httpRequest.uri().substring(httpRequest.uri().indexOf("?")), "GBK");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

        }


        //若是Post请求
        if (requestMethod.equals(HttpMethod.POST)) {
            if (msg instanceof LastHttpContent) {
                // TODO: 2019/8/4 因post中body分多种，在未确定使用哪种种类之前，暂不写其中的解析
            }
        }

        //todo 选择functionId处理类
        if (!executeMap.containsKey(functionId)) {

        }

        String responseString = "";
        sendResponse(ctx, responseString, HttpResponseStatus.OK);

    }

    /**
     * 发送返回数据
     *
     * @param ctx            通道句柄上下文
     * @param responseString 返回字符串
     */
    private void sendResponse(ChannelHandlerContext ctx, String responseString, HttpResponseStatus httpResponseStatus) {
        //构造返回
        FullHttpResponse response = null;
        response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                httpResponseStatus,
                Unpooled.wrappedBuffer(responseString.getBytes(StandardCharsets.UTF_8)));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        ctx.write(response);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        cause.printStackTrace();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
