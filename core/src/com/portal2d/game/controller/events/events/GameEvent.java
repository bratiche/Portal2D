package com.portal2d.game.controller.events.events;

import com.portal2d.game.controller.events.listeners.EventListener;

/**
 * Base interface for all events. When an event happens, notify is called for each of the listeners of the event.
 */
public interface GameEvent<T extends EventListener> {

    //TODO: hay que hacer que GameEvent sea una clase y tenga una variable handled(boolean) para que los eventos no se
    // repitan infinitamente mientras se cumple la condicion
    public void notify(T listener);
}
