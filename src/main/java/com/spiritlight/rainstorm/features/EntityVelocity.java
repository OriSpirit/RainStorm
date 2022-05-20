package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class EntityVelocity extends Mod implements EventHandler.Listener {
    public static String modName = "EntityVelocity";
    // Remove listener if unneeded, add to __init__ if needed
    @Override
    public void onEvent(Event event) {
        if(!enabled || !(event instanceof InputEvent.KeyInputEvent)) return;
        try {
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            if(!player.isRiding() || player.getRidingEntity() == null || player.getRidingEntity() instanceof EntityPlayerMP) return;
            Entity vehicle = player.getRidingEntity();
            vehicle.setVelocity(vehicle.motionX*=1.5, vehicle.motionY, vehicle.motionZ*=1.5);
        } catch (NullPointerException ignored) {}
    }
    public static void enable() { enabled = true; }
    public static void disable() { enabled = false; }

}
