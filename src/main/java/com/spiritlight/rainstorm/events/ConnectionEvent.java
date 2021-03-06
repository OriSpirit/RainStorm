package com.spiritlight.rainstorm.events;

import com.spiritlight.rainstorm.MainMod;
import com.spiritlight.rainstorm.util.PacketHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class ConnectionEvent {
    @SubscribeEvent
    public void onServerConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        MainMod.isConnected = true;
        event.getManager().channel().pipeline().addBefore("packet_handler", "spirit_packet_handler", new PacketHandler());
        System.out.println("Added packet handler to channel pipeline.");
    }

    @SubscribeEvent
    public void onServerDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        MainMod.isConnected = false;
        event.getManager().channel().pipeline().remove("spirit_packet_handler");
        FeatureManager.blink.disableRP(); // Specifically here to disable these mods
    }
}
