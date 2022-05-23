package com.spiritlight.rainstorm.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;
import java.util.List;

public class BlockPosUtils {
    public BlockPos edit(BlockPos pos, double x, double y, double z) {
        return new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
    }

    public BlockPos edit(BlockPos pos, Vec3i vector) {
        return new BlockPos(pos.add(vector));
    }

    public BlockPos edit(BlockPos pos, Vec3d vector) {
        return new BlockPos(pos.getX() + vector.x, pos.getY() + vector.y, pos.getZ() + vector.z);
    }

    public Vec3i getVec3i(BlockPos pos) {
        return new Vec3i(pos.getX(), pos.getY(), pos.getZ());
    }

    public Vec3d getVec3d(BlockPos pos) {
        return new Vec3d(pos.getX(), pos.getY(), pos.getZ());
    }

    public List<BlockPos> getSurroundingBlocks(BlockPos pos) {
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

    public String[] toStringArray(BlockPos pos) {
        return new String[] {String.valueOf(pos.getX()), String.valueOf(pos.getY()), String.valueOf(pos.getZ())};
    }

    public int[] toIntArray(BlockPos pos) {
        return new int[] {pos.getX(), pos.getY(), pos.getZ()};
    }

    public double[] toDoubleArray(BlockPos pos) {
        return new double[] {pos.getX(), pos.getY(), pos.getZ()};
    }

    public Vec3d toVec3d(double[] val) {
        return new Vec3d(val[0], val[1], val[2]);
    }
}
