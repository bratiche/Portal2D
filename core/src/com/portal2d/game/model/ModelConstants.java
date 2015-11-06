package com.portal2d.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public interface ModelConstants {

    /**
     * Constants for updating the box2d world
     */
    interface Box2D {
        Vector2 DEFAULT_GRAVITY = new Vector2(0, -9.8f);

        //iterations for updating the World
        int VELOCITY_ITERATIONS = 6; //8
        int POSITION_ITERATIONS = 2; //3
    }

    //TODO: test terminal velocity
    float TERMINAL_VELOCITY = 15.0f;

    // Radius for grabbing objects with the gravity gun
    float GRAVITY_GUN_RADIUS = 0.7f;

    float PLAYER_MAX_VELOCITY = 2.0f;

    float TURRET_VISION_RADIUS = 3.0f;
    // Rate of fire in seconds
    float TURRET_RATE_OF_FIRE = 0.7f;

    // Length of each ray cast step
    float RAY_CAST_STEP_LENGTH = 0.1f;

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

    float BULLET_SPEED = 1.0f;
    float BULLET_RADIUS = 0.1f;
    float PROJECTILE_RADIUS = 0.1f;

}
