package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        if(!(e instanceof PlayerInteractEvent)) return;
        PlayerInteractEvent event = (PlayerInteractEvent) e;
        // second check to prevent duplication of events
        if(!enabled || event.getHand() == EnumHand.OFF_HAND) return;
        // event.getHand() == EnumHand.MAIN_HAND
        // 3 +x; 1 -x; 0 +z; 2 -z
        if((event instanceof PlayerInteractEvent.RightClickItem && event.getHand() == EnumHand.MAIN_HAND || event instanceof PlayerInteractEvent.RightClickEmpty && event.getHand() == EnumHand.MAIN_HAND)) {
            final RayTraceBlock rayTrace = new RayTraceBlock();
            int direction = (Objects.equals(rayTrace.getBlock(), Material.AIR) ? -1 : MathHelper.floor(((playerUtils.getPlayer().rotationYaw * 4F) / 360F) + 0.5D) & 3);
            int modX = (direction == 3 ? -1 : (direction == 1 ? 1 : 0));
            int modY = (direction != -1 ? 1 : 0);
            int modZ = (direction == 0 ? -1 : (direction == 2 ? 1 : 0));
            try {
                if((rayTrace.getBlock() == Material.AIR || rayTrace.getBlock() == null) && !playerUtils.getPlayer().isSneaking()) {
                    Messenger.send("Distance specified too far (Hold shift to force teleport)");
                    return;
                }
                playerUtils.teleportCenter(blockPosUtils.edit(rayTrace.getPos(), 0.5 + modX, modY, 0.5 + modZ));
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
