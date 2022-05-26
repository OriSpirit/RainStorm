package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.events.FeatureManager;

public class __init__ {
    private static boolean initFinish = false;
    public static void run() {
        if(!initFinish) {
            initFinish = true;
            reset();
        }
    }

    public static void reset() {
        FeatureManager.blockTP.disable();
        FeatureManager.noFall.disable();
        FeatureManager.fly.disable();
        FeatureManager.entityVelocity.disable();
    }
}
