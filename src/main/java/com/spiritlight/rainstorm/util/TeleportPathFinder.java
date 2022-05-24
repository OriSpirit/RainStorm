package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.constants.Features;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@ParametersAreNonnullByDefault
public class TeleportPathFinder {
    final BlockPosUtils blockPosUtils = new BlockPosUtils();
    // Returns an ordered ArrayList as a teleportation sequence
    public @Nullable List<BlockPos> findOptimalPath(BlockPos destination) {
        int steps = 0;
        Set<BlockPos> sequence = new HashSet<>();
        Set<BlockPos> scannedBlockPos = new HashSet<>();
        List<BlockPos> result = new ArrayList<>();
        BlockPos initialStep = Minecraft.getMinecraft().player.getPosition();
        sequence.add(initialStep);
        while(steps < Features.PathFinder.MAX_STEPS) {
            Set<BlockPos> scanList = new HashSet<>();
            for(BlockPos pos : sequence) {
                sequence.addAll(blockPosUtils.getSurroundingBlocks(pos));
            }
            sequence.addAll(scanList);
            sequence.removeAll(scannedBlockPos);
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

    private BlockPos getShortestPath(Set<BlockPos> sequenceList, BlockPos destination) {
        final BlockUtils blockUtils = new BlockUtils();
        Map<Double, BlockPos> distance = new HashMap<>();
        double shortestDistance = Double.MAX_VALUE;
        for(BlockPos pos : sequenceList) {
            if(blockUtils.isBlockSolid(pos))
                continue;
            shortestDistance = Math.min(blockUtils.getDistance(pos, destination), shortestDistance);
            distance.put(blockUtils.getDistance(pos, destination), pos);
        }
        return distance.get(shortestDistance);
    }
}
