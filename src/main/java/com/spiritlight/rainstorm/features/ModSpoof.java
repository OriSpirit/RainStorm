package com.spiritlight.rainstorm.features;

import com.spiritlight.rainstorm.event.Mod;
import com.spiritlight.rainstorm.util.EventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ModSpoof extends Mod implements EventHandler.Listener {
    @Override
    public void onEvent(Event event) {
        if(!enabled) return;
    }
    public static void enable() { enabled = true; }
    public static void disable() { enabled = false; }
}
