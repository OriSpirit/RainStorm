package com.spiritlight.rainstorm.util;

import io.netty.channel.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

import java.util.LinkedList;

public class PacketUtils {
    private static final LinkedList<Packet<? extends INetHandler>> queuedPackets = new LinkedList<>();

    public static void stopPacket() {
        final Minecraft minecraft = Minecraft.getMinecraft();
        final ChannelDuplexHandler packetHandler = new ChannelDuplexHandler() {
            public void write(final ChannelHandlerContext context, final Object packet, final ChannelPromise promise) {
                if(packet instanceof Packet)
                    queuedPackets.add((Packet<? extends INetHandler>) packet);
            }
        };
        final ChannelPipeline pipeline = minecraft.getConnection().getNetworkManager().channel().pipeline();
        pipeline.addBefore("packet_handler", "packet_disabler", packetHandler);
    }

    public static void startPacket(boolean abandonAllPackets) {
        if(abandonAllPackets)
            queuedPackets.clear();
        final Minecraft minecraft = Minecraft.getMinecraft();
        final NetHandlerPlayClient networkHandler = minecraft.getConnection();
        final Channel channel = networkHandler.getNetworkManager().channel();
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove("packet_disabler");
        });
        for (int size = queuedPackets.size(), i = 0; i < size; ++i) {
            networkHandler.sendPacket(queuedPackets.getFirst());
            queuedPackets.removeFirst();
        }
    }
}
