package com.spiritlight.rainstorm.events;


import java.util.ArrayList;

import com.spiritlight.rainstorm.event.CancellableEvent;


public interface TickListener extends Listener
{
    void onTick();

    class TickEndEvent
            extends CancellableEvent<TickListener>
    {

        public TickEndEvent()
        {
        }

        @Override
        public void fire(ArrayList<TickListener> listeners)
        {
            for(TickListener listener : listeners)
            {
                listener.onTick();

                if(isCancelled())
                    break;
            }
        }

        @Override
        public Class<TickListener> getListenerType()
        {
            return TickListener.class;
        }
    }
}