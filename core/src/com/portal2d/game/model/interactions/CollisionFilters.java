package com.portal2d.game.model.interactions;

import com.badlogic.gdx.physics.box2d.Filter;

/**
 * Filter constants for Box2D collision detection.
 * @see Filter
 */
public interface CollisionFilters {

    short PLAYER_BITS = 2;
    short PORTAL_BITS = 8;


    //example, TODO: remove
    //              player      pp            portal
    // category   0000 0001   0000 0010     0000 0100
    // mask       1111 1101   1111 1010     1111 1101
}
