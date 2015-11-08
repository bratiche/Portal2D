package com.portal2d.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public interface ModelConstants {

    /* Globals */
    float TERMINAL_VELOCITY = 15.0f; // Maximum velocity on y direction
    float RAY_CAST_STEP_LENGTH = 0.1f; // Length of each ray cast step

    /* Player */
    float PLAYER_WIDTH = 0.6f;
    float PLAYER_HEIGHT = 1.2f;
    float PLAYER_MAX_VELOCITY = 2.0f;

    /* Box */
    float BOX_WIDTH = 0.6f;
    float BOX_HEIGHT = 0.6f;

    /* Gravity gun */
    float GRAVITY_GUN_RADIUS = 0.7f; // Radius for grabbing objects with the gravity gun

    /* Turret */
    float TURRET_WIDTH = 0.6f;
    float TURRET_HEIGHT = 0.6f;
    float TURRET_VISION_RADIUS = 3.0f;
    float TURRET_RATE_OF_FIRE = 0.7f;  // Rate of fire in seconds

    /* Bullet */
    float BULLET_SPEED = 1.0f;
    float BULLET_RADIUS = 0.1f;

    /* Button */
    float BUTTON_WIDTH = 1.2f;
    float BUTTON_HEIGHT = 0.1f;

    /* Gate */
    float GATE_WIDTH = 0.4f;
    float GATE_HEIGHT = 1.8f;

    /* Portal */
    float PORTAL_RADIUS = 0.1f;

    /* Exit */
    float EXIT_WIDTH = 0.3f;
    float EXIT_HEIGHT = 1.8f;

    float TILESIZE = 0.6f;

    /**
     * Constants for updating the box2d world
     */
    interface Box2D {
        Vector2 DEFAULT_GRAVITY = new Vector2(0, -9.8f);
        int VELOCITY_ITERATIONS = 6; //8
        int POSITION_ITERATIONS = 2; //3
    }

}
