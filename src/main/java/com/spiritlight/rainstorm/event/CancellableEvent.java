package com.spiritlight.rainstorm.event;

import com.spiritlight.rainstorm.events.Listener;

public abstract class CancellableEvent<T extends Listener> extends Event<T>
{
    private boolean cancelled = false;

    public void cancel()
    {
        cancelled = true;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }
}