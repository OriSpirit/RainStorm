package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import com.spiritlight.rainstorm.util.Messenger;
import com.spiritlight.rainstorm.util.RayTraceBlock;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public final class BlockTP extends Mod implements EventHandler.Listener {
    static boolean enabled;
    static BlockTP self = new BlockTP();

    public static void enable() {
        Messenger.send("Right-Click on a block to teleport, left click to cancel.");
        EventHandler.add(self);
    }

    public static void disable() {
        EventHandler.remove(self);
    }

    public static void setEnabled(boolean b) {
        enabled = b;
    }

    @Override
    public void onEvent(Event event) {
        if(event instanceof PlayerInteractEvent.RightClickItem || event instanceof PlayerInteractEvent.RightClickBlock || event instanceof PlayerInteractEvent.RightClickEmpty)
        try {
            Minecraft.getMinecraft().player.setPosition(RayTraceBlock.getPos().getX(), RayTraceBlock.getPos().getY(), RayTraceBlock.getPos().getZ());
        } catch (NullPointerException ignored) {
            Messenger.send("Block too far.");
        }
        if(event instanceof PlayerInteractEvent.LeftClickBlock || event instanceof PlayerInteractEvent.LeftClickEmpty) {
            disable();
        }
    }
}
