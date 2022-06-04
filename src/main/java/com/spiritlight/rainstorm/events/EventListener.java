package com.spiritlight.rainstorm.events;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventListener {
    private static Event event;
    @SubscribeEvent
    public void onEvent(Event event) {
        EventListener.event = event;
    }

    public static Event getEvent() {
        return event;
    }

}