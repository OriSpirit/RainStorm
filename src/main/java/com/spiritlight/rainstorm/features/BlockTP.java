package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import com.spiritlight.rainstorm.util.Messenger;
import com.spiritlight.rainstorm.util.RayTraceBlock;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public final class BlockTP extends Mod implements EventHandler.Listener {
    static boolean enabled = false;

    public static void enable() {
        if(!enabled) {
            Messenger.send("Right-Click on a block to teleport, left click to cancel.");
            enabled = true;
        } else {
            Messenger.send("BlockTP is active.");
        }
    }

    public static void disable() {
        if(enabled) {
            Messenger.send("Disabled BlockTP.");
            enabled = false;
        } else {
            Messenger.send("BlockTP is not active.");
        }
    }

    @Override
    public void onEvent(Event event) {
        if(!enabled)
            return;
        if(event instanceof PlayerInteractEvent.RightClickItem || event instanceof PlayerInteractEvent.RightClickEmpty) {
            try {
                Minecraft.getMinecraft().player.setPosition(RayTraceBlock.getPos().getX() + 0.5, RayTraceBlock.getPos().getY(), RayTraceBlock.getPos().getZ() + 0.5);
            } catch (NullPointerException ignored) {
                Messenger.send("Invalid block.");
            }
        }
        if(event instanceof PlayerInteractEvent.LeftClickBlock || event instanceof PlayerInteractEvent.LeftClickEmpty) {
            disable();
        }
    }
}
