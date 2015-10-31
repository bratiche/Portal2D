package com.portal2d.game.model.interactions;

/**
 * Filters constants for Box2d collision detection.
 */
public interface CollisionFilters {

    short PLAYER_BITS = 2;
    short PORTAL_PROJECTILE_BITS = 4;


    //              player      pp
    // category   0000 0001   0000 0010
    // mask       1111 1101   1111 1110
}
