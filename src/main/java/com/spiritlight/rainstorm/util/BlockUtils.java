package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.constants.Features;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BlockUtils {

    public BlockPos TargetBlock() {
        boolean hasCrossedBlock = false;
        PlayerUtils playerUtils = new PlayerUtils();
        BlockPosUtils blockPosUtils = new BlockPosUtils();
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        Vec3d playerFacingPos = player.getLookVec().normalize();
        // Vec3d playerFacingPos = VecTransformer.Vector3d.edit(player.getLookVec().normalize(), 0, -player.getLookVec().normalize().y, 0);
        BlockPos playerLookingAt = playerUtils.getPlayerCameraPos();
        Material material = TargetMaterial(playerLookingAt);
        double[] pos = blockPosUtils.toDoubleArray(playerLookingAt);
        int iterations = 0;
        while((!hasCrossedBlock || (material != Material.AIR && playerLookingAt != playerUtils.getPlayerCameraPos())) && iterations < Features.PathFinder.MAX_LOOKUP) {
            pos[0] += playerFacingPos.x;
            pos[1] += playerFacingPos.y;
            pos[2] += playerFacingPos.z;
            playerLookingAt = new BlockPos(blockPosUtils.toVec3d(pos));
            // Messenger.send("Current checking location: " + Arrays.toString(blockPosUtils.toStringArray(playerLookingAt)));
            material = TargetMaterial(blockPosUtils.edit(playerLookingAt, 0, -playerLookingAt.getY(), 0));
            if((isGroundGround(playerLookingAt) || !isBlockSolid(playerLookingAt)) && hasCrossedBlock)
                break;
            if(isBlockSolid(playerLookingAt)){
                hasCrossedBlock = true;
            }
            iterations++;
        }
        return playerLookingAt;
    }

    public Material TargetMaterial(BlockPos pos) {
        return Minecraft.getMinecraft().world.getBlockState(pos).getMaterial();
    }

    public double getDistance(BlockPos from, BlockPos to) {
        int dx = Math.abs(from.getX() - to.getX());
        int dy = Math.abs(from.getY() - to.getY());
        int dz = Math.abs(from.getZ() - to.getZ());
        return get3DLength(dx, dy, dz);
    }

    public Material getBlockAt(BlockPos pos) {
        return Minecraft.getMinecraft().world.getBlockState(pos).getMaterial();
    }

    private double get3DLength(int x, int y, int z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public boolean isGroundGround(BlockPos pos) {
        return checkBlockOfRange(pos, 0, -2, 0, Material.AIR, 1);
    }

    public boolean isBlockSolid(BlockPos pos) {
        return getBlockAt(pos).blocksMovement();
    }

    // 0: EQUALS, 1: NOTEQUALS
    public boolean checkBlockOfRange(BlockPos initPosition, int dx, int dy, int dz, Material instance, int Operation) {
        final BlockPosUtils blockPosUtils = new BlockPosUtils();
        for(int x = 0;(dx < 0 ? x > -dx : x < dx); x+=(dx < 0 ? -1 : 1))
            for(int y = 0;(dy < 0 ? y > -dy : x < dy); y+=(dy < 0 ? -1 : 1))
                for(int z = 0;(dz < 0 ? z > -dz : z < dz); z+=(dz < 0 ? -1 : 1))
                    switch(Operation) {
                        case 0:
                            if(TargetMaterial(blockPosUtils.edit(initPosition, x, y, z)).equals(instance))
                                return true;
                            break;
                        case 1:
                            if(!TargetMaterial(blockPosUtils.edit(initPosition, x, y, z)).equals(instance))
                                return true;
                        default:
                            throw new IllegalArgumentException("Operation must be 0 (equals) or 1 (not equals)");
                    }
        return false;
    }
}