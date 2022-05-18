package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

// only for reference uses
public class NoFall extends Mod implements EventHandler.Listener {
    static boolean enabled = false;
    // Remove listener if unneeded, add to __init__ if needed
    @Override
    public void onEvent(Event event) {
        if(!enabled) return;
        if(Minecraft.getMinecraft().player.fallDistance > 2) {
            try {
                Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayer(true));
            } catch (NullPointerException ignored) {}
        }
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }
}
