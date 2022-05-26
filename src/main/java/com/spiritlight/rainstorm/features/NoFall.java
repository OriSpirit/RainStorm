package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.CPacketPlayer;

public class NoFall extends Mod implements Listener {
    public String modName = "NoFall";
    @Override
    public void onUpdate() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if (player == null) return;
        if (!enabled || !player.isAirBorne || player.capabilities.isCreativeMode) return;
        if(player.motionY < -0.5) {
            try {
                Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayer(true));
            } catch (NullPointerException ignored) {}
        }
    }
}
