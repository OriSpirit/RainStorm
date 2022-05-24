package com.spiritlight.rainstorm.util;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault @SuppressWarnings("unused")
public class VecTransformer {
    public static class Vector3d {
        public static Vec3d toVec3d(Vec3i vector) {
            return new Vec3d(vector.getX(), vector.getY(), vector.getZ());
        }

        public static Vec3d edit(Vec3d vector, double x, double y, double z) {
            return new Vec3d(vector.x + x, vector.y + y, vector.z + z);
        }
    }

    public static class Vector3i {
        public static Vec3i toVec3i(Vec3d vector) {
            return new Vec3i(vector.x, vector.y, vector.z);
        }

        public static Vec3i edit(Vec3i vector, double x, double y, double z) {
            return new Vec3i(vector.getX() + x, vector.getY() + y, vector.getZ() + z);
        }
    }
}
