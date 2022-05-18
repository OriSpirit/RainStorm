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

    public static void add(Listener l) {
        listeners.add(l);
    }

    public static void remove(Listener l) {
        listeners.remove(l);
    }


    @SubscribeEvent
    public void event(Event event) {
        for (Listener l : listeners) {
            l.onEvent(event);
        }
    }
}
