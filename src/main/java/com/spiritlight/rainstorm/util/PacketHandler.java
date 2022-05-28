package com.spiritlight.rainstorm.util;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.Packet;

import java.util.ArrayList;
import java.util.List;

public class PacketHandler extends ChannelDuplexHandler {
    List<String> discardPacketsR = new ArrayList<>();
    List<String> discardPacketsW = new ArrayList<>();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        if(obj instanceof Packet) {
            for(String s : discardPacketsR) {
                if(obj.getClass().getName().contains(s)) {
                    return;
                }
            }
        }
        super.channelRead(ctx, obj);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object obj, ChannelPromise promise) throws Exception {
        if(obj instanceof Packet) {
                for(String s : discardPacketsW) {
                    if(obj.getClass().getName().contains(s)) {
                        return;
                    }
                }
            }
        super.write(ctx, obj, promise);
    }
}
