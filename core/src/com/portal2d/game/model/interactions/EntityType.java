package com.portal2d.game.model.interactions;

import com.portal2d.game.model.entities.Entity;

/**
 *
 */

public enum EntityType {

    BOX,
    EXIT,
    GATE,
    PORTAL,
    PLAYER,
    BUTTON,
    PROJECTILE,
    PORTAL_PROJECTILE,
    SURFACE,
    PORTABLE_SURFACE


    //TODO: discuss whether to use this or have all methods in Entity
//    public abstract void beginInteraction(Entity e1, Entity e2);
//
//    public abstract void endInteraction(Entity e1, Entity e2);

}
