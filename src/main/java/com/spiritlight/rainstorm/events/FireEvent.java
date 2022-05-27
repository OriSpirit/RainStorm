package com.spiritlight.rainstorm.events;

public class FireEvent {
    public static void tickEvent(perTickEvent event) {
        event.onUpdate();
    }
}
