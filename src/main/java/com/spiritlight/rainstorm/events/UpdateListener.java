package com.spiritlight.rainstorm.events;

import com.spiritlight.rainstorm.event.Event;
import com.spiritlight.rainstorm.event.Listener;

import java.util.ArrayList;

public interface UpdateListener extends Listener
{
    void onUpdate();

    class UpdateEvent extends Event<UpdateListener>
    {
        public static final UpdateEvent INSTANCE = new UpdateEvent();

        @Override
        public void fire(ArrayList<UpdateListener> listeners)
        {
            for(UpdateListener listener : listeners)
                listener.onUpdate();
        }

        @Override
        public Class<UpdateListener> getListenerType()
        {
            return UpdateListener.class;
        }
    }
}