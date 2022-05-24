package com.spiritlight.rainstorm.features;

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
        BlockTP.disable();
        NoFall.disable();
        Fly.disable();
        EntityVelocity.disable();
        HuntedGlow.disable();
    }
}
