package com.spiritlight.rainstorm.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.List;

public class BlockPosUtils {
    public static BlockPos edit(BlockPos pos, double x, double y, double z) {
        return new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
    }

    public static BlockPos edit(BlockPos pos, Vec3i vector) {
        return new BlockPos(pos.add(vector));
    }

    public static Vec3i getVec3i(BlockPos pos) {
        return new Vec3i(pos.getX(), pos.getY(), pos.getZ());
    }

    public static List<BlockPos> getSurroundingBlocks(BlockPos pos) {
        List<BlockPos> result = new ArrayList<>();
        result.add(pos);
        result.add(edit(pos, 1, 0, 0));
        result.add(edit(pos, 0, 1, 0));
        result.add(edit(pos, 0, 0, 1));
        result.add(edit(pos, -1, 0, 0));
        result.add(edit(pos, 0, -1, 0));
        result.add(edit(pos, 0, 0, -1));
        return result;
    }

    public static String[] toStringArray(BlockPos pos) {
        return new String[] {String.valueOf(pos.getX()), String.valueOf(pos.getY()), String.valueOf(pos.getZ())};
    }
}
