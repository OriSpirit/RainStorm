package com.spiritlight.rainstorm.util;

import com.spiritlight.rainstorm.constants.Features;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class BlockUtils {
    public static BlockPos TargetBlock(RayTraceResult result) {
        final EntityPlayerSP player = Minecraft.getMinecraft().player;
        Vec3d playerFacingPos = player.getLookVec().normalize();
        BlockPos playerLookingAt = PlayerUtils.getPlayerCameraPos();
        Material material = TargetMaterial(playerLookingAt);
        int iterations = 0;
        while(material != Material.GROUND && material != Material.AIR && iterations < Features.PathFinder.MAX_LOOKUP) {
            playerLookingAt.add(VecTransformer.Vector3i.toVec3i(playerFacingPos));
            material = TargetMaterial(playerLookingAt);
            iterations++;
        }
        return playerLookingAt;
    }

    public static Material TargetMaterial(BlockPos pos) {
        return Minecraft.getMinecraft().world.getBlockState(pos).getMaterial();
    }
}