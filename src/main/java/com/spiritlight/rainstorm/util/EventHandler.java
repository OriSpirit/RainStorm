package com.spiritlight.rainstorm.util;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;


public class EventHandler {
    public interface Listener {
        void onEvent(Event event);
    }

    private static final List<Listener> listeners = new ArrayList<>();

    // We don't remove to prevent ConcurrentModificationExceptions for now
    public static void add(Listener l) {
        if(!listeners.contains(l))
            listeners.add(l);
    }

    @SubscribeEvent
    public void event(Event event) {
        for (Listener it : listeners) {
            it.onEvent(event);
        }
    }
}
