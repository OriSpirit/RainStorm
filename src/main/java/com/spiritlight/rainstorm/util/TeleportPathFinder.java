package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.constants.Features;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import java.util.*;

public class TeleportPathFinder {
    private static int steps = 0;
    // Returns an ordered ArrayList as a teleportation sequence
    public static ArrayList<BlockPos> findOptimalPath(BlockPos destination) {
        boolean isFound = false;
        Set<BlockPos> sequence = new HashSet<>();
        Set<BlockPos> scannedBlockPos = new HashSet<>();
        BlockPos initialStep = Minecraft.getMinecraft().player.getPosition();
        BlockPos destinationBlock = BlockUtils.TargetBlock(RayTraceBlock.getResult());
        while(steps < Features.PathFinder.MAX_STEPS && !isFound) {
            sequence.addAll(scannedBlockPos);
            sequence.add(initialStep);
            sequence.removeAll(scannedBlockPos);
            BlockPos pos = getShortestPath(sequence);

            steps++;
            scannedBlockPos.addAll(new HashSet<>(sequence));
            sequence.clear();
        }
        return null;
    }

    private static BlockPos getShortestPath(@Nonnull Set<BlockPos> sequenceList) {
        Map<Double, BlockPos> distance = new HashMap<>();
        double shortestDistance = Double.MAX_VALUE;
        for(BlockPos pos : sequenceList) {
            shortestDistance = Math.min(getLength(pos), shortestDistance);
            distance.put(getLength(pos), pos);
        }
        return distance.get(shortestDistance);
    }

    private static double getLength(BlockPos pos) {
        return Math.sqrt(Math.pow(pos.getX(), 2) + Math.pow(pos.getY(), 2) + Math.pow(pos.getZ(), 2));
    }
}
