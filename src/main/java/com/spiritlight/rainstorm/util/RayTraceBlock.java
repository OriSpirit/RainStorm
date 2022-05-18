package com.spiritlight.rainstorm.util;

import net.minecraft.block.state.BlockWorldState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import javax.annotation.Nullable;

public class RayTraceBlock {
    private static final EntityPlayerSP player = Minecraft.getMinecraft().player;
    private static final double maxTraceRadius = 36.0d;
    public static @Nullable BlockPos getPos() {
        RayTraceResult block = player.rayTrace(maxTraceRadius, 0.0f);
        return block != null ? block.getBlockPos() : null;
    }

    public static @Nullable BlockWorldState getState() {
        RayTraceResult block = player.rayTrace(maxTraceRadius, 0.0f);
        return block != null ? (BlockWorldState) player.world.getBlockState(block.getBlockPos()) : null;
    }
}
