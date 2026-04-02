package org.wallentines.fbha.mixin;

import io.netty.channel.Channel;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.wallentines.fbha.HAProxyHandler;

import com.llamalad7.mixinextras.sugar.Local;

@Mixin(targets = { "net.minecraft.server.network.ServerConnectionListener$1" })
public class MixinChannelInitializer {

    @Inject(method="initChannel", at=@At("TAIL"))
    private void onServerStart(Channel channel, CallbackInfo ci, @Local Connection conn) {
        channel.pipeline().addFirst(new HAProxyHandler(conn));
        channel.pipeline().addFirst(new HAProxyMessageDecoder());
    }


}
