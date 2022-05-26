package com.spiritlight.rainstorm.events;

import com.spiritlight.rainstorm.event.Event;
import com.spiritlight.rainstorm.event.iListener;

import java.util.ArrayList;

public interface Listener extends iListener
{
    void onUpdate();

    class UpdateEvent extends Event<Listener>
    {
        public static final UpdateEvent INSTANCE = new UpdateEvent();

        @Override
        public void fire(ArrayList<Listener> listeners)
        {
            for(Listener listener : listeners)
                listener.onUpdate();
        }

        @Override
        public Class<Listener> getListenerType()
        {
            return Listener.class;
        }
    }
}