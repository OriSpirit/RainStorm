package com.spiritlight.rainstorm.event;

@SuppressWarnings("unused")
public abstract class Mod implements ModBase {
    protected static boolean enabled = false;
    public static void enable() { enabled = true; }
    public static void disable() { enabled = false; }
    public static boolean isEnabled() {
        return enabled;
    }
    public static boolean toggle() {
        enabled = !enabled;
        if(enabled)
            enable();
        else disable();
        return enabled; }
}
