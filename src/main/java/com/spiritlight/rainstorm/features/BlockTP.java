package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Arrays;
import java.util.List;

public final class BlockTP extends Mod implements EventHandler.Listener {
    public static String modName = "BlockTP";
    final static BlockPosUtils blockPosUtils = new BlockPosUtils();
    final static BlockUtils blockUtils = new BlockUtils();
    final static TeleportPathFinder finder = new TeleportPathFinder();
    final static PlayerUtils playerUtils = new PlayerUtils();

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
    public void onEvent(Event e) {
        if(!(e instanceof  PlayerInteractEvent)) return;
        PlayerInteractEvent event = (PlayerInteractEvent) e;
        // second check to prevent duplication of events
        if(!enabled || event.getHand() == EnumHand.OFF_HAND) return;
        // event.getHand() == EnumHand.MAIN_HAND
        if((event instanceof PlayerInteractEvent.RightClickItem && event.getHand() == EnumHand.MAIN_HAND || event instanceof PlayerInteractEvent.RightClickEmpty && event.getHand() == EnumHand.MAIN_HAND)) {
            final RayTraceBlock rayTrace = new RayTraceBlock();
            try {
                if((rayTrace.getBlock() == Material.AIR || rayTrace.getBlock() == null) && !playerUtils.getPlayer().isSneaking()) {
                    Messenger.send("Distance specified too far (Hold shift to force teleport)");
                    return;
                }
                Minecraft.getMinecraft().player.setPosition(rayTrace.getPos().getX() + 0.5, rayTrace.getPos().getY(), rayTrace.getPos().getZ() + 0.5);
            } catch (NullPointerException ignored) {
                Messenger.send("Invalid block specified.");
            }
        }
        if(event instanceof PlayerInteractEvent.RightClickBlock && event.getHand() == EnumHand.MAIN_HAND) {
            // Handle pathfinding here
            Messenger.send("Attempting to go through " + Arrays.toString(blockPosUtils.toStringArray(blockUtils.TargetBlock())));
            List<BlockPos> sequence = finder.findOptimalPath(blockUtils.TargetBlock());
            if(playerUtils.getPlayer().isSneaking()) {
                playerUtils.teleport(blockUtils.TargetBlock());
            } else {
                if(sequence == null) {
                    Messenger.send("No available pathing for the destination " + Arrays.toString(blockPosUtils.toStringArray(blockUtils.TargetBlock())) + ", shift-click to force teleport.");
                    return;
                }
                for (BlockPos b : sequence) {
                    playerUtils.teleportCenter(b);
                }
            }
        }
        if(event instanceof PlayerInteractEvent.LeftClickBlock || event instanceof PlayerInteractEvent.LeftClickEmpty) {
            disable();
            event.setCanceled(true);
        }
    }
}
