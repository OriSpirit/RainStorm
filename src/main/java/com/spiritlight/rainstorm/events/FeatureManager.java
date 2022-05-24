package com.spiritlight.rainstorm.events;

import com.spiritlight.rainstorm.features.*;

public class FeatureManager {
    // Register mods here
    public static final Blink blink = new Blink();
    public static final BlockTP blockTP = new BlockTP();
    public static final EntityVelocity entityVelocity = new EntityVelocity();
    public static final Fly fly = new Fly();
    public static final GhostBlock ghostBlock = new GhostBlock();
    public static final HuntedGlow huntedGlow = new HuntedGlow();
    public static final NoFall noFall = new NoFall();
}
