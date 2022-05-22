package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Arrays;
import java.util.List;

public final class BlockTP extends Mod implements EventHandler.Listener {
    public static String modName = "BlockTP";

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
        }
        enabled = false;
    }

    @Override
    public void onEvent(Event event) {
        if(!enabled) return;
        if(event instanceof PlayerInteractEvent.RightClickItem || event instanceof PlayerInteractEvent.RightClickEmpty) {
            try {
                if(RayTraceBlock.getBlock() == Material.AIR || RayTraceBlock.getBlock() == null) {
                    Messenger.send("Distance specified too far.");
                    return;
                }
                Minecraft.getMinecraft().player.setPosition(RayTraceBlock.getPos().getX() + 0.5, RayTraceBlock.getPos().getY(), RayTraceBlock.getPos().getZ() + 0.5);
            } catch (NullPointerException ignored) {}
        }
        if(event instanceof PlayerInteractEvent.RightClickBlock) {
            // Handle pathfinding here
            List<BlockPos> sequence = TeleportPathFinder.findOptimalPath(BlockUtils.TargetBlock());
            if(sequence == null) {
                Messenger.send("No available pathing for the destination " + Arrays.toString(BlockPosUtils.toStringArray(BlockUtils.TargetBlock())));
                return;
            }
            for(BlockPos b : sequence) {
                Minecraft.getMinecraft().player.setPosition(b.getX()+0.5, b.getY(), b.getZ()+0.5);
            }
        }
        if(event instanceof PlayerInteractEvent.LeftClickBlock || event instanceof PlayerInteractEvent.LeftClickEmpty) {
            disable();
        }
    }
}
