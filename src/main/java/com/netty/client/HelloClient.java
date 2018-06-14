package com.netty.client;

import com.netty.server.HelloServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloClient {
    public static String host = "127.0.0.1";
    public static int port = 8888;

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .handler(new HelloClientInitalizer());
            Channel ch = b.connect(host, port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String line = in.readLine();
                if (null == line) {
                    continue;
                }
                ch.writeAndFlush(line + "\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
