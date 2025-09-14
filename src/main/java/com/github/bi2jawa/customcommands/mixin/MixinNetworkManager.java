package com.github.bi2jawa.customcommands.mixin;

import com.github.bi2jawa.customcommands.events.PacketReceived;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S3APacketTabComplete;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

/*
 * Adapted from Skytils under GNU Affero General Public License v3.0.
 * Adapted by ToborWinner then again by me (bi2)
*/

@Mixin(value = NetworkManager.class, priority = 1005)
public abstract class MixinNetworkManager extends SimpleChannelInboundHandler<Packet<?>> {
    @Shadow
    @Final
    private EnumPacketDirection direction;

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onReceivePacket_CustomCommandMod(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) throws IOException {
        if (this.direction != EnumPacketDirection.CLIENTBOUND) return;
        if (!(packet instanceof S3APacketTabComplete))
            return;
        // Call packet received event
        PacketReceived event = new PacketReceived(packet);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }
}
