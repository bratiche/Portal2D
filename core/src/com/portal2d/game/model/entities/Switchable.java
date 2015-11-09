package com.portal2d.game.model.entities;

/**
 * Interface implemented by anything that can be switched on and off.
 * @see Gate
 */
public interface Switchable {

    /** Called when a switch linked to this is pressed */
    public void switchOn();

    /** Called when a switch linked to this is released. */
    public void switchOff();
}
