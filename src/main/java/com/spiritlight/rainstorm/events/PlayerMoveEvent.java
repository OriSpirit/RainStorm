package com.spiritlight.rainstorm.events;


import java.util.ArrayList;

import com.spiritlight.rainstorm.event.CancellableEvent;

// This is just a base event, nothing should hook to it.
// Code again from https://github.com/Wurst-Imperium/Wurst-MC-1.12
public interface PlayerMoveListener extends Listener
{
    void eventName(PlayerMoveEvent event);

    class PlayerMoveEvent
            extends CancellableEvent<PlayerMoveListener>
    {
        private final PlayerMoveEvent obj;

        public PlayerMoveEvent(PlayerMoveEvent event)
        {
            this.obj = event;
        }

        public Object methodName()
        {
            return obj;
        }

        @Override
        public void fire(ArrayList<PlayerMoveListener> listeners)
        {
            for(PlayerMoveListener listener : listeners)
            {
                listener.eventName(this);

                if(isCancelled())
                    break;
            }
        }

        @Override
        public Class<PlayerMoveEvent> getListenerType()
        {
            return PlayerMoveEvent.class;
        }
    }
}