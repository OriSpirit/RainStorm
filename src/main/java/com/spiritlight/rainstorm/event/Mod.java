package com.spiritlight.rainstorm.event;

@SuppressWarnings("unused")
public abstract class Mod implements ModBase {
    private static boolean enabled;
    public static void enable() { enabled = true; }
    public static void disable() { enabled = false; }
    public static boolean isEnabled() {
        return enabled;
    }
}
