package com.spiritlight.rainstorm.event;

@SuppressWarnings("unused")
public abstract class Mod implements ModBase {
    protected boolean enabled = false;
    public void enable() { enabled = true; }
    public void disable() { enabled = false; }
    public boolean isEnabled() {
        return enabled;
    }
    public void toggle() {
        enabled = !enabled;
        if(enabled)
            enable();
        else disable();
    }
}
