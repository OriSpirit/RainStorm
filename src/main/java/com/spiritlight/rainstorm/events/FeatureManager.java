package com.spiritlight.rainstorm.events;

import com.spiritlight.rainstorm.features.*;

public class FeatureManager {
    public static Blink blink;
    public static BlockTP blockTP;
    public static EntityVelocity entityVelocity;
    public static Fly fly;
    public static GhostBlock ghostBlock;
    public static NoFall noFall;
    public static Spider spider;
    private FeatureManager() {
        blink = new Blink();
        blockTP = new BlockTP();
        entityVelocity = new EntityVelocity();
        fly = new Fly();
        ghostBlock = new GhostBlock();
        noFall = new NoFall();
        spider = new Spider();
    }
}
