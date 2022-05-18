package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import com.spiritlight.rainstorm.util.Messenger;
import com.spiritlight.rainstorm.util.RayTraceBlock;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ConcurrentModificationException;

public final class BlockTP extends Mod implements EventHandler.Listener {
    static BlockTP self = new BlockTP();
    static boolean enabled = false;

    public static void enable() {
        Messenger.send("Right-Click on a block to teleport, left click to cancel.");
        EventHandler.add(self);
        enabled = true;
    }

    public static void disable() {
        try {
            EventHandler.remove(self);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
        Messenger.send("Disabled BlockTP.");
        enabled = false;
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof PlayerInteractEvent.RightClickItem || event instanceof PlayerInteractEvent.RightClickBlock || event instanceof PlayerInteractEvent.RightClickEmpty)
        try {
            Minecraft.getMinecraft().player.setPosition(RayTraceBlock.getPos().getX() + 0.5, RayTraceBlock.getPos().getY(), RayTraceBlock.getPos().getZ() + 0.5);
        } catch (NullPointerException ignored) {
            Messenger.send("Block too far.");
        }
        if(event instanceof PlayerInteractEvent.LeftClickBlock || event instanceof PlayerInteractEvent.LeftClickEmpty) {
            disable();
        }
    }
}
