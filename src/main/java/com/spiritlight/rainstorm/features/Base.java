package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;

// only for reference uses, REMOVE ANNOTATION WHEN USING
@SuppressWarnings("all")
public class Base extends Mod implements EventHandler.Listener {
    public static String modName = "Base";
    // Remove listener if unneeded, add to __init__ if needed
    @Override
    public void onEvent(Event event) {
        if(!enabled) return;
    }
    public static void enable() { enabled = true; }
    public static void disable() { enabled = false; }

}
