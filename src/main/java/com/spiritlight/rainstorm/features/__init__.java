package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.util.EventHandler;

public class __init__ {
    private static boolean initFinish = false;
    public static void run() {
        if(!initFinish) {
            EventHandler.add(new BlockTP());
            initFinish = true;
            reset();
        }
    }

    public static void reset() {
        BlockTP.disable();
    }
}
