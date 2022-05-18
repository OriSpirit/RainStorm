package com.spiritlight.rainstorm.event;

public interface ModBase {
    boolean enabled = false;
    static void enable() {}
    static void disable() {}
    static boolean isEnabled() { return enabled; }
}
