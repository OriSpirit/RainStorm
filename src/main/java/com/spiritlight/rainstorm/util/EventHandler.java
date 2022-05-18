package com.spiritlight.rainstorm.util;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.List;

public class EventHandler extends EventListener {
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

    public static void event() {
        for (Listener l : listeners) {
            l.onEvent(EventListener.getEvent());
        }
    }
}
