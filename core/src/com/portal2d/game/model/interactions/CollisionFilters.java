package com.portal2d.game.model.interactions;

import com.badlogic.gdx.physics.box2d.Filter;

/**
 * Filter constants for Box2D collision detection.
 * @see Filter
 */
public interface CollisionFilters {

    short PLAYER_BITS = 2;
    short BOX_BITS = 4;
    short PORTAL_BITS = 8;

}
