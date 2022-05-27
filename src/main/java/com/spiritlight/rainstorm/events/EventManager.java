package com.spiritlight.rainstorm.events;

import java.util.*;

import com.spiritlight.rainstorm.event.Event;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;

// Credits to: https://github.com/Wurst-Imperium/Wurst-MC-1.12
public final class EventManager
{
    private final HashMap<Class<? extends Listener>, ArrayList<? extends Listener>> listenerMap =
            new HashMap<>();

    public <L extends Listener, E extends Event<L>> void fire(E event)
    {

        try
        {
            Class<L> type = event.getListenerType();
            @SuppressWarnings("unchecked")
            ArrayList<L> listeners = (ArrayList<L>)listenerMap.get(type);

            if(listeners == null || listeners.isEmpty())
                return;

            // Creating a copy of the list to avoid concurrent modification
            // issues.
            ArrayList<L> listeners2 = new ArrayList<>(listeners);

            // remove() sets an element to null before removing it. When one
            // thread calls remove() while another calls fire(), it is possible
            // for this list to contain null elements, which need to be filtered
            // out.
            listeners2.removeIf(Objects::isNull);

            event.fire(listeners2);

        }catch(Throwable e)
        {
            e.printStackTrace();

            CrashReport report =
                    CrashReport.makeCrashReport(e, "Firing event");
            CrashReportCategory category =
                    report.makeCategory("Affected event");
            category.addDetail("Event class", () -> event.getClass().getName());

            throw new ReportedException(report);
        }
    }

    public <L extends Listener> void add(Class<L> type, L listener)
    {
        try
        {
            @SuppressWarnings("unchecked")
            ArrayList<L> listeners = (ArrayList<L>)listenerMap.get(type);

            if(listeners == null)
            {
                listeners = new ArrayList<>(Collections.singletonList(listener));
                listenerMap.put(type, listeners);
                return;
            }

            listeners.add(listener);

        }catch(Throwable e)
        {
            e.printStackTrace();

            CrashReport report =
                    CrashReport.makeCrashReport(e, "Adding event listener");
            CrashReportCategory category =
                    report.makeCategory("Affected listener");
            category.addDetail("Listener type", type::getName);
            category.addDetail("Listener class",
                    () -> listener.getClass().getName());

            throw new ReportedException(report);
        }
    }

    public <L extends Listener> void remove(Class<L> type, L listener)
    {
        try
        {
            @SuppressWarnings("unchecked")
            ArrayList<L> listeners = (ArrayList<L>)listenerMap.get(type);

            if(listeners != null)
                listeners.remove(listener);

        }catch(Throwable e)
        {
            e.printStackTrace();

            CrashReport report =
                    CrashReport.makeCrashReport(e, "Removing event listener");
            CrashReportCategory category =
                    report.makeCategory("Affected listener");
            category.addDetail("Listener type", type::getName);
            category.addDetail("Listener class",
                    () -> listener.getClass().getName());

            throw new ReportedException(report);
        }
    }
}