package com.spiritlight.rainstorm.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;

public class PlayerUtils {
    public BlockPos getPlayerCameraPos() {
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        return new BlockPos(player.getPosition().getX(), player.getPosition().getY() + player.eyeHeight, player.getPosition().getZ());
    }

    public BlockPos getCurrentCameraBlock() {
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        BlockPosUtils blockPosUtils = new BlockPosUtils();
        return new BlockPos(blockPosUtils.edit(player.getPosition(), 0, player.getEyeHeight(), 0));
    }

    public EntityPlayerSP getPlayer() {
        return Minecraft.getMinecraft().player;
    }

    public void teleport(BlockPos pos) {
        EntityPlayerSP player = getPlayer();
        player.setPosition(pos.getX(), pos.getY(), pos.getZ());
    }

    public void teleportCenter(BlockPos pos) {
        EntityPlayerSP player = getPlayer();
        player.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }
}
