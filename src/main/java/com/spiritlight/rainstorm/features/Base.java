package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.Listener;

// only for reference uses, REMOVE ANNOTATION WHEN USING
@SuppressWarnings("all")
public class Base extends Mod implements Listener {
    public String modName = "Base";
    // Remove listener if unneeded, add to __init__ if needed
    @Override
    public void onUpdate() {
        if(!enabled) return;
    }
    public void enable() { enabled = true; }
    public void disable() { enabled = false; }

}
