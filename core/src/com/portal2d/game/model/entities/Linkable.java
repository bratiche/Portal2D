package com.portal2d.game.model.entities;

/**
 * Interface implemented by anything that can be linked to a {@link Button}.
 */
public interface Linkable {

    /** Called when a button linked to this is pressed */
    public void link();

    /** Called when a button linked to this is released. */
    public void unlink();
}
