package com.spiritlight.rainstorm.util;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ParametersAreNonnullByDefault
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

    public BlockPos arrayToPos(double [] d) {
        if(d.length != 3) throw new IllegalArgumentException("Array size must be 3, found " + d.length);
        return new BlockPos(d[0], d[1], d[2]);
    }

    public BlockPos arrayToPos(int [] d) {
        if(d.length != 3) throw new IllegalArgumentException("Array size must be 3, found " + d.length);
        return new BlockPos(d[0], d[1], d[2]);
    }

    public Vec3d toVec3d(double [] val) {
        return new Vec3d(val[0], val[1], val[2]);
    }

    public BlockPos getValidTeleportBlock(BlockPos pos) {
        final BlockUtils blockUtils = new BlockUtils();
        if(blockUtils.getBlockAt(pos).equals(Material.AIR))
            return pos;
        final RayTraceBlock rayTrace = new RayTraceBlock();
        final PlayerUtils playerUtils = new PlayerUtils();
        int direction = (Objects.equals(rayTrace.getBlock(), Material.AIR) ? -1 : MathHelper.floor(((playerUtils.getPlayer().rotationYaw * 4F) / 360F) + 0.5D) & 3);
        int modX = (direction == 3 ? -1 : (direction == 1 ? 1 : 0));
        int modY = (direction != -1 ? 1 : 0);
        int modZ = (direction == 0 ? -1 : (direction == 2 ? 1 : 0));
        return edit(pos, modX, modY, modZ);
    }
}
