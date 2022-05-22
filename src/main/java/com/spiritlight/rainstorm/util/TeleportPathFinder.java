package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.constants.Features;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import java.util.*;

public class TeleportPathFinder {
    final BlockPosUtils blockPosUtils = new BlockPosUtils();
    final BlockUtils blockUtils = new BlockUtils();
    // Returns an ordered ArrayList as a teleportation sequence
    public List<BlockPos> findOptimalPath(BlockPos destination) {
        int steps = 0;
        Set<BlockPos> sequence = new HashSet<>();
        Set<BlockPos> scannedBlockPos = new HashSet<>();
        List<BlockPos> result = new ArrayList<>();
        BlockPos initialStep = Minecraft.getMinecraft().player.getPosition();
        while(steps < Features.PathFinder.MAX_STEPS) {
            Set<BlockPos> scanList = new HashSet<>();
            sequence.addAll(scannedBlockPos);
            sequence.add(initialStep);
            sequence.removeAll(scannedBlockPos);
            for(BlockPos pos : sequence) {
                scanList.addAll(blockPosUtils.getSurroundingBlocks(pos));
            }
            BlockPos shortestPath = getShortestPath(scanList, destination);
            result.add(shortestPath);
            if(shortestPath == destination) {
                return result;
            }
            steps++;
            scannedBlockPos.addAll(new HashSet<>(sequence));
            sequence.clear();
        }
        return null;
    }

    private BlockPos getShortestPath(@Nonnull Set<BlockPos> sequenceList, @Nonnull BlockPos destination) {
        Map<Double, BlockPos> distance = new HashMap<>();
        double shortestDistance = Double.MAX_VALUE;
        for(BlockPos pos : sequenceList) {
            shortestDistance = Math.min(blockUtils.getDistance(pos, destination), shortestDistance);
            distance.put(blockUtils.getDistance(pos, destination), pos);
        }
        return distance.get(shortestDistance);
    }
}
