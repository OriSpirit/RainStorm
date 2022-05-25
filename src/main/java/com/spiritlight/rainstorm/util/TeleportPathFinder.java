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
    final BlockUtils blockUtils = new BlockUtils();
    Set<BlockPos> scannedBlockPos = new HashSet<>();
    // Returns an ordered ArrayList as a teleportation sequence
    public @Nullable List<BlockPos> findOptimalPath(BlockPos destination) {
        int steps = 0;
        Set<BlockPos> sequence = new HashSet<>();
        List<BlockPos> result = new ArrayList<>();
        Set<BlockPos> scanList = new HashSet<>();
        BlockPos initialStep = Minecraft.getMinecraft().player.getPosition();
        sequence.add(initialStep);
        while(steps < Features.PathFinder.MAX_STEPS) {
            sequence.addAll(scanList);
            scanList.clear();
            for(BlockPos pos : sequence) {
                if(blockUtils.isBlockSolid(pos)) continue; // do not add these
                scanList.addAll(blockPosUtils.getSurroundingBlocks(pos));
            }
            scanList.removeAll(scannedBlockPos);
            final BlockPos shortestPath = getShortestPath(scanList, destination);
            result.add(shortestPath);
            if(blockPosUtils.posEquals(shortestPath, destination) || blockPosUtils.equalsFloor(shortestPath, destination)) {
                return result;
            }
            steps++;
            scannedBlockPos.addAll(scanList);
            Messenger.send("Scan >> Total scanned " + scannedBlockPos.size() + " blocks");
            Messenger.send("Scan >> Collected teleport path of " + result.size() + " blocks");
        }
        return null;
    }

    private BlockPos getShortestPath(Set<BlockPos> sequenceList, BlockPos destination) {
        final BlockUtils blockUtils = new BlockUtils();
        Map<Double, BlockPos> distance = new HashMap<>();
        double shortestDistance = Double.MAX_VALUE;
        for(BlockPos pos : sequenceList) {
            if(blockUtils.isBlockSolid(pos) || scannedBlockPos.contains(pos))
                continue;
            shortestDistance = Math.min(blockUtils.getDistance(pos, destination), shortestDistance);
            distance.put(blockUtils.getDistance(pos, destination), pos);
        }
        Messenger.send("Found blockPos " + Arrays.toString(blockPosUtils.toStringArray(distance.get(shortestDistance))));
        return distance.get(shortestDistance);
    }
}
