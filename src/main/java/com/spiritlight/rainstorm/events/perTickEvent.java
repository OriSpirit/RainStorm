package com.spiritlight.rainstorm.events;


import java.util.ArrayList;

import com.spiritlight.rainstorm.event.CancellableEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public interface perTickEvent extends Listener
{
    void onTick(TickEndEvent event);

    class TickEndEvent
            extends CancellableEvent<perTickEvent>
    {

        public TickEndEvent()
        {
        }

        @Override
        public void fire(ArrayList<perTickEvent> listeners)
        {
            for(perTickEvent listener : listeners)
            {
                listener.onTick(this);

                if(isCancelled())
                    break;
            }
        }

        @Override
        public Class<perTickEvent> getListenerType()
        {
            return perTickEvent.class;
        }
    }
}