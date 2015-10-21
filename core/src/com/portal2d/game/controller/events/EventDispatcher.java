package com.portal2d.game.controller.events;

import com.portal2d.game.controller.events.events.GameEvent;
import com.portal2d.game.controller.events.listeners.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public final class EventDispatcher {

    private Map<Class, List> map;

    public EventDispatcher() {
        map = new HashMap<Class, List>();
    }

    /**
     * Adds a new Listener mapped with the class of the corresponding Event (register/subscribe)
     */
    public <T extends EventListener> void addListener(Class< ? extends GameEvent<T>> eventClass, T listener) {
        List<T> listeners = listenersOf(eventClass);

        if(!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * Removes the listener for the given event class (unregister/unsubscribe)
     */
    public <T extends EventListener> void removeListener(Class<? extends GameEvent<T>> eventClass, T listener) {
        ArrayList<T> listeners = (ArrayList<T>) listenersOf(eventClass);
        listeners.remove(listener);
    }

    /**
     * Returns the listeners of the given Event Class
     */
    private <T extends EventListener> List<T> listenersOf(Class<? extends GameEvent<T>> eventClass) {

        ArrayList<T> listeners = (ArrayList<T>) map.get(eventClass);

        if(listeners != null)
            return listeners;

        listeners = new ArrayList<T>();
        map.put(eventClass, listeners);
        return listeners;
    }

    /**
     * Notifies all the listeners of the given event.
     */
    public <T extends EventListener> void notifyEvent(GameEvent<T> event) {

        Class<GameEvent<T>> eventClass = (Class<GameEvent<T>>) event.getClass();

        for(T listener : listenersOf(eventClass)) {
            event.notify(listener);
        }

    }

}
