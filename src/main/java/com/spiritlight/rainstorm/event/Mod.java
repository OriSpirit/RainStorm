package com.spiritlight.rainstorm.event;

@SuppressWarnings("unused")
public abstract class Mod {
    private static boolean enabled;
    public static void enable() {}
    public static void disable() {}
    public static boolean isEnabled() {
        return enabled;
    }
}
