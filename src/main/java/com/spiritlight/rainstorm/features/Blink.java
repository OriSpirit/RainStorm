package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.Messenger;
import io.netty.channel.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

import java.util.LinkedList;

public class Blink extends Mod {
    public String modName = "Blink";
    private final LinkedList<Packet<? extends INetHandler>> queuedPackets = new LinkedList<>();

    @Override
    public void enable() {
        Messenger.send("Enabled blink");
        final Minecraft minecraft = Minecraft.getMinecraft();
        final ChannelDuplexHandler packetHandler = new ChannelDuplexHandler() {
            public void write(final ChannelHandlerContext context, final Object packet, final ChannelPromise promise) {
                if(packet instanceof Packet)
                    queuedPackets.add((Packet<? extends INetHandler>) packet);
            }
        };
        final ChannelPipeline pipeline = minecraft.getConnection().getNetworkManager().channel().pipeline();
        pipeline.addBefore("packet_handler", "packet_disabler", packetHandler);
        enabled = true;
    }

    @Override
    public void disable() {
        Messenger.send("Disabled blink");
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
        enabled = false;
    }

    public void disableRP() {
        queuedPackets.clear();
        disable();
    }
}
