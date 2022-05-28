package com.spiritlight.rainstorm.events;

import java.util.ArrayList;
import com.spiritlight.rainstorm.event.CancellableEvent;

public interface UpdateListener extends Listener
{
    void onUpdate();

    class UpdateListenerEvent
            extends CancellableEvent<UpdateListener>
    {
        @Override
        public void fire(ArrayList<UpdateListener> listeners)
        {
            for(UpdateListener listener : listeners)
            {
                listener.onUpdate();
                if(isCancelled())
                    break;
            }
        }

        @Override
        public Class<UpdateListener> getListenerType()
        {
            return UpdateListener.class;
        }
    }
}