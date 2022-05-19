package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

// only for reference uses
public class NoFall extends Mod implements EventHandler.Listener {
    static boolean enabled = false;
    // Remove listener if unneeded, add to __init__ if needed
    @Override
    public void onEvent(Event event) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player == null) return;
        if (!enabled || !player.isAirBorne || player.capabilities.isCreativeMode) return;
        if(player.motionY < -0.5) {
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
