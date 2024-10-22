package org.wallentines.fbha.mixin;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.wallentines.fbha.HAProxyHandler;

@Mixin(targets = { "net.minecraft.server.network.ServerConnectionListener$1" })
public class MixinChannelInitializer {

    @Inject(method="initChannel", at=@At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void onServerStart(Channel channel, CallbackInfo ci, ChannelPipeline pipeline, Connection conn) {
        channel.pipeline().addFirst("haproxy_handler", new HAProxyHandler(conn));
        channel.pipeline().addFirst("haproxy_decoder", new HAProxyMessageDecoder());
    }


}
