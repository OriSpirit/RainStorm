package com.spiritlight.rainstorm;

import com.spiritlight.rainstorm.events.EventManager;

// Init here
public enum Spirit {
    INSTANCE;

    public EventManager events;

    public void onStart() {
        events = new EventManager();
    }
}