package com.spiritlight.rainstorm.network;

import com.spiritlight.rainstorm.config.status;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.Packet;

public class PacketHandler extends ChannelDuplexHandler {
    @Override
    public void write(ChannelHandlerContext ctx, Object obj, ChannelPromise promise) throws Exception {
        if(obj instanceof Packet && status.cancelNextInventoryPacket && obj.getClass().getName().contains("CPacketCloseWindow")) {
            status.cancelNextInventoryPacket = false;
            return;
        }
        super.write(ctx, obj, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        super.channelRead(ctx, obj);
    }
}