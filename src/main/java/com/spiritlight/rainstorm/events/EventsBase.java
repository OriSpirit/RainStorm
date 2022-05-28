package com.spiritlight.rainstorm.events;


import java.util.ArrayList;

import com.spiritlight.rainstorm.event.CancellableEvent;

// This is just a base event, nothing should hook to it.
// Code again from https://github.com/Wurst-Imperium/Wurst-MC-1.12
public interface EventsBase extends Listener
{
    void eventName(EventsBaseEvent event);

    class EventsBaseEvent
            extends CancellableEvent<EventsBase>
    {
        private final Object obj;

        public EventsBaseEvent(Object obj)
        {
            this.obj = obj;
        }

        public Object getObj()
        {
            return obj;
        }

        @Override
        public void fire(ArrayList<EventsBase> listeners)
        {
            for(EventsBase listener : listeners)
            {
                listener.eventName(this);

                if(isCancelled())
                    break;
            }
        }

        @Override
        public Class<EventsBase> getListenerType()
        {
            return EventsBase.class;
        }
    }
}