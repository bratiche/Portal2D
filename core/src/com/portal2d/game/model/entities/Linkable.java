package com.portal2d.game.model.entities;

/**
 * Interface implemented by any entity that can be linked to a button.
 */
public interface Linkable {

    //connect
    public void link();

    //disconnect
    public void unlink();
}
