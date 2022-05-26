package com.spiritlight.rainstorm.event;

import java.util.ArrayList;

public abstract class Event<T extends iListener>
{
    public abstract void fire(ArrayList<T> listeners);

    public abstract Class<T> getListenerType();
}