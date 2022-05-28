package com.spiritlight.rainstorm.events;

import com.spiritlight.rainstorm.features.*;

public class FeatureManager {
    public static Blink blink = new Blink();;
    public static BlockTP blockTP = new BlockTP();
    public static EntityVelocity entityVelocity = new EntityVelocity();
    public static Fly fly = new Fly();
    public static GhostBlock ghostBlock = new GhostBlock();
    public static NoFall noFall = new NoFall();
    public static Spider spider = new Spider();
    private FeatureManager() {}
}
