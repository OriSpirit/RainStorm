package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.events.FeatureManager;
import com.spiritlight.rainstorm.util.EventHandler;

public class __init__ {
    private static boolean initFinish = false;
    public static void run() {
        if(!initFinish) {
            EventHandler.add(new NoFall());
            EventHandler.add(new EntityVelocity());
            initFinish = true;
            reset();
        }
    }

    public static void reset() {
        FeatureManager.blockTP.disable();
        FeatureManager.noFall.disable();
        FeatureManager.fly.disable();
        FeatureManager.entityVelocity.disable();
        FeatureManager.huntedGlow.disable();
    }
}
