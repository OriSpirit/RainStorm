package com.spiritlight.rainstorm.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class BPTransformer {
    public static BlockPos edit(BlockPos pos, double x, double y, double z) {
        return new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
    }

    public static BlockPos edit(BlockPos pos, Vec3i vector) {
        return new BlockPos(pos.add(vector));
    }
}
