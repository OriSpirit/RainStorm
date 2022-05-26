package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class EntityVelocity extends Mod implements Listener {
    public String modName = "EntityVelocity";
    // Remove listener if unneeded, add to __init__ if needed
    @Override
    public void onUpdate() {
        if(!enabled) return;
        try {
            final Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            if(!player.isRiding() || player.getRidingEntity() == null || player.getRidingEntity() instanceof EntityPlayerMP) return;
            Entity vehicle = player.getRidingEntity();
            if(mc.gameSettings.keyBindLeft.isPressed() || mc.gameSettings.keyBindBack.isPressed() || mc.gameSettings.keyBindForward.isPressed() || mc.gameSettings.keyBindRight.isPressed()) {
                vehicle.setVelocity(vehicle.motionX*=1.5, vehicle.motionY, vehicle.motionZ*=1.5);
            }

        } catch (NullPointerException ignored) {}
    }
}
