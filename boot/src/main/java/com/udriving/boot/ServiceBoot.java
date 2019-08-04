package com.udriving.boot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import lombok.AllArgsConstructor;

/**
 * 服务器启动类
 */
@AllArgsConstructor
public class ServiceBoot {
    /**
     * 端口
     */
    private int port;

    /**
     * 主入口
     *
     * @param args 参数数组
     */
    public static void main(String[] args) {
        if (args.length >= 1) {
            System.out.println("启动端口号：" + args[0]);
            new ServiceBoot(Integer.parseInt(args[0])).bootService();
        }
    }

    private void bootService() {
        //用于服务器端接受客户端的连接
        EventLoopGroup bossGruop = new NioEventLoopGroup();
        //用于处理请求
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGruop, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel arg0) {
                            arg0.pipeline()
                                    .addLast(new HttpResponseEncoder(),
                                            new HttpRequestDecoder(),
                                            new RequestDistribute());
                        }
                    })
                    //指定此套接口排队的最大连接个数
                    .option(ChannelOption.SO_BACKLOG, 1024);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGruop.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
