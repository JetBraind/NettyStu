package com.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "Says : " + msg);
        ctx.writeAndFlush("Receive your msg! \n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RemoteAddress: " + ctx.channel().remoteAddress() + " active!");
        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + "service!\n");
        super.channelActive(ctx);
    }
}
