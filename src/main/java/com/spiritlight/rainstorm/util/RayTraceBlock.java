package com.spiritlight.rainstorm.util;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import javax.annotation.ParametersAreNullableByDefault;

@ParametersAreNullableByDefault @SuppressWarnings("unused")
public class RayTraceBlock {
    private final EntityPlayerSP player = Minecraft.getMinecraft().player;
    private final double maxTraceRadius = 36.0d;
    public BlockPos getPos() {
        RayTraceResult block = player.rayTrace(maxTraceRadius, 0.0f);
        return block != null ? block.getBlockPos() : null;
    }

    public IBlockState getBlockState() {
        RayTraceResult block = player.rayTrace(maxTraceRadius, 0.0f);
        return block != null ? player.world.getBlockState(block.getBlockPos()) : null;
    }

    public Material getBlock() {
        RayTraceResult block = player.rayTrace(maxTraceRadius, 0.0f);
        return block != null ? player.world.getBlockState(block.getBlockPos()).getMaterial() : null;
    }

    public RayTraceResult getResult() {
        return player.rayTrace(maxTraceRadius, 0.0f);
    }
}
