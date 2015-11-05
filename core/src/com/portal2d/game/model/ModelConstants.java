package com.portal2d.game.model;

/**
 *
 */
public interface ModelConstants {

    //TODO: test terminal velocity
    float TERMINAL_VELOCITY = 15.0f;

    // Radius fot the player to grab objects
    float GRAB_RADIUS = 0.5f;

    float PLAYER_MAX_VELOCITY = 2.0f;

    float TURRET_VISION_RADIUS = 3.0f;

    // Dimension of each ray cast
    float RAY_CAST_STEP = 0.1f;

    //TODO: add physical properties of each entity

    float TILESIZE = 0.6f;

    float PLAYER_WIDTH = 0.6f;
    float PLAYER_HEIGHT = 1.2f;

    float BOX_WIDTH = 0.6f;
    float BOX_HEIGHT = 0.6f;

    float BUTTON_WIDTH = 1.2f;
    float BUTTON_HEIGHT = 0.1f;

    float GATE_WIDTH = 0.4f;
    float GATE_HEIGHT = 1.8f;

    float EXIT_WIDTH = 0.3f;
    float EXIT_HEIGHT = 1.8f;

    float TURRET_WIDTH = 0.6f;
    float TURRET_HEIGHT = 0.6f;

    float PORTAL_RADIUS = 0.1f; //Con 0.6 no hay problemas

    float PROJECTILE_SPEED = 1.0f;
    float PROJECTILE_RADIUS = 0.1f;

}
