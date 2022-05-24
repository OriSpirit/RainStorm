package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public final class BlockTP extends Mod {
    public String modName = "BlockTP";

    @Override
    public void enable() {
        if(!enabled) {
            Messenger.send("Right-Click on a block to teleport, left click to cancel.");
            enabled = true;
        } else {
            Messenger.send("BlockTP is active.");
        }
    }

    @Override
    public void disable() {
        if(enabled) {
            Messenger.send("Disabled BlockTP.");
            enabled = false;
        }
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickEmpty event) {
        if (!enabled || event.getHand() == EnumHand.OFF_HAND) return;
        final RayTraceBlock rayTrace = new RayTraceBlock();
        final BlockPosUtils blockPosUtils = new BlockPosUtils();
        final PlayerUtils playerUtils = new PlayerUtils();
        try {
            if ((rayTrace.getBlock() == Material.AIR || rayTrace.getBlock() == null) && !playerUtils.getPlayer().isSneaking()) {
                Messenger.send("Distance specified too far (Hold shift to force teleport)");
                return;
            }
            playerUtils.teleportCenter(blockPosUtils.getValidTeleportBlock(rayTrace.getPos()));
        } catch (NullPointerException ignored) {
            Messenger.send("Invalid block specified.");
        }
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!enabled || event.getHand() == EnumHand.OFF_HAND) return;
        final BlockPosUtils blockPosUtils = new BlockPosUtils();
        final PlayerUtils playerUtils = new PlayerUtils();
        final BlockUtils blockUtils = new BlockUtils();
        final TeleportPathFinder finder = new TeleportPathFinder();
        // Handle pathfinding here
        Messenger.send("Attempting to go through " + Arrays.toString(blockPosUtils.toStringArray(blockUtils.TargetBlock())));
        List<BlockPos> sequence = finder.findOptimalPath(blockUtils.TargetBlock());
        if (playerUtils.getPlayer().isSneaking()) {
            playerUtils.teleportCenter(blockPosUtils.getValidTeleportBlock(blockUtils.TargetBlock()));
        } else {
            if (sequence == null) {
                Messenger.send("No available pathing for the destination " + Arrays.toString(blockPosUtils.toStringArray(blockUtils.TargetBlock())) + ", shift-click to force teleport.");
                return;
            }
            for (BlockPos b : sequence) {
                playerUtils.teleportCenter(blockPosUtils.getValidTeleportBlock(b));
            }
        }
        event.setCanceled(true);
    }

    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        if (!enabled || event.getHand() == EnumHand.OFF_HAND) return;
        disable();
    }

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (!enabled || event.getHand() == EnumHand.OFF_HAND) return;
        disable();
    }
}
