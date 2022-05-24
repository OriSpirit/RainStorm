package com.spiritlight.rainstorm.events;

import com.spiritlight.rainstorm.features.*;

public class FeatureManager {
    // Register mods here
    public static Blink blink = new Blink();
    public static BlockTP blockTP = new BlockTP();
    public static EntityVelocity entityVelocity = new EntityVelocity();
    public static Fly fly = new Fly();
    public static GhostBlock ghostBlock = new GhostBlock();
    public static HuntedGlow huntedGlow = new HuntedGlow();
    public static NoFall noFall = new NoFall();
}
