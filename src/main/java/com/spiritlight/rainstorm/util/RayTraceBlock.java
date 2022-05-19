package com.spiritlight.rainstorm.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
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

    public static @Nullable IBlockState getBlockState() {
        RayTraceResult block = player.rayTrace(maxTraceRadius, 0.0f);
        return block != null ? player.world.getBlockState(block.getBlockPos()) : null;
    }

    public static @Nullable Material getBlock() {
        RayTraceResult block = player.rayTrace(maxTraceRadius, 0.0f);
        return block != null ? player.world.getBlockState(block.getBlockPos()).getMaterial() : null;
    }
}
