package com.spiritlight.rainstorm.util;

import net.minecraft.block.state.BlockWorldState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

import javax.annotation.Nullable;

public class RayTraceBlock {
    private static final EntityPlayerSP player = Minecraft.getMinecraft().player;
    private static final RayTraceResult block = player.rayTrace(20.0d, 0.0f);
    public static @Nullable BlockPos getPos() {
        return block != null ? block.getBlockPos() : null;
    }

    public static @Nullable BlockWorldState getState() {
        return block != null ? (BlockWorldState) player.world.getBlockState(block.getBlockPos()) : null;
    }
}
