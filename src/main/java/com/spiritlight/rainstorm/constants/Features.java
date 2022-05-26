package com.spiritlight.rainstorm.constants;

public class Features {
    public static final class RayTrace {
        public static final double MAX_DISTANCE = 36.0d;
    }
    public static final class PathFinder {
        public static final int MAX_STEPS = 36; // Grows exponentially, not recommended being too high
        public static final int MAX_LOOKUP = 35; // Lookup up to 35 blocks on TargetBlock
    }
}
