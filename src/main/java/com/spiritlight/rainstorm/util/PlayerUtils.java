package com.spiritlight.rainstorm.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;

public class PlayerUtils {
    public BlockPos getPlayerCameraPos() {
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        return new BlockPos(player.getPosition().getX(), player.getPosition().getY() + player.eyeHeight, player.getPosition().getZ());
    }
}
