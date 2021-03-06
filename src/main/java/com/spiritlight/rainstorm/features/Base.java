package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.events.EventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;


// only for reference uses, REMOVE ANNOTATION WHEN USING
@SuppressWarnings("all")
public class Base extends Mod implements EventHandler.Listener {
    public String modName = "Base";
    // Remove listener if unneeded, add to __init__ if needed

    public void onUpdate() {
        if(!enabled) return;
    }
    public void enable() { enabled = true; }
    public void disable() { enabled = false; }

    @Override
    public void onEvent(Event event) {

    }
}
