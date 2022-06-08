package com.spiritlight.rainstorm.util;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.Packet;

import java.util.ArrayList;
import java.util.List;
public class PacketHandler extends ChannelDuplexHandler {
    private static final List<String> discardPacketsR = new ArrayList<>();
    private static final List<String> discardPacketsW = new ArrayList<>();
    public static final String READ = "read";
    public static final String WRITE = "write";
    @Override // Receive packet
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        if(obj instanceof Packet) {
            final List<String> dpr = new ArrayList<>(discardPacketsR);
            for(String s : dpr) {
                if(obj.getClass().getName().contains(s)) {
                    discardPacketsR.remove(s);
                    return;
                }
            }
        }
        super.channelRead(ctx, obj);
    }

    @Override // Send packet
    public void write(ChannelHandlerContext ctx, Object obj, ChannelPromise promise) throws Exception {
        if(obj instanceof Packet) {
            final List<String> dpw = new ArrayList<>(discardPacketsW);
                for(String s : dpw) {
                    if(obj.getClass().getName().contains(s)) {
                        discardPacketsW.remove(s);
                        return;
                    }
                }
            }
        super.write(ctx, obj, promise);
    }

    public static boolean discardPacket(String packetName, String type) {
        if(!packetName.contains("Packet")) return false;
        try {
            switch (type) {
                case READ:
                    discardPacketsR.add(packetName);
                    break;
                case WRITE:
                    discardPacketsW.add(packetName);
                    break;
                default:
                    return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
