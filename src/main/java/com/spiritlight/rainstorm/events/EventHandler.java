package com.spiritlight.rainstorm.events;

import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventHandler extends EventListener {
    public interface Listener {
        void onEvent(Event event);
    }

    private final List<Listener> listeners = new ArrayList<>();

    public void add(Listener l) {
        listeners.add(l);
    }

    public void remove(Listener l) {
        listeners.remove(l);
    }

    public void event() {
        ArrayList<Listener> listeners2 = new ArrayList<>(listeners);
        listeners.removeIf(Objects::isNull);
        for (Listener l : listeners2) {
            l.onEvent(EventListener.getEvent());
        }
    }
}