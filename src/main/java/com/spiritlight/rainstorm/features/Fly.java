package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.Messenger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class Fly extends Mod {
    public String modName = "Fly";

    @Override
    public void enable() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player == null) return;
        if(!player.capabilities.isCreativeMode) {
            player.capabilities.allowFlying = true;
            enabled = true;
        } else {
            Messenger.send("This plugin is disabled for creative mode players.");
            enabled = false;
        }
    }

    @Override
    public void disable() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        if(player == null) return;
        if(!player.capabilities.isCreativeMode) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
        } else {
            Messenger.send("This plugin is disabled for creative mode players.");
        }
        enabled = false;
    }
}
