package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;

// only for reference uses
public class Base extends Mod implements EventHandler.Listener {
    // Remove listener if unneeded, add to __init__ if needed
    @Override
    public void onEvent(Event event) {
        if(!enabled) return;
    }
    public static void enable() {}
    public static void disable() {}
}
