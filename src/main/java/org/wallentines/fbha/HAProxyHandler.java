package org.wallentines.fbha;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.haproxy.HAProxyMessage;
import net.minecraft.network.Connection;
import org.wallentines.fbha.mixin.AccessorConnection;

import java.net.InetSocketAddress;

public class HAProxyHandler extends SimpleChannelInboundHandler<HAProxyMessage> {

    private final Connection connection;

    public HAProxyHandler(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HAProxyMessage msg) throws Exception {

        ((AccessorConnection) connection).setAddress(new InetSocketAddress(msg.sourceAddress(), msg.sourcePort()));
        ctx.pipeline().remove(this);
    }
}
