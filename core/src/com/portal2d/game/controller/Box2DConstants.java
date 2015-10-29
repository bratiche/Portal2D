package com.portal2d.game.controller;

import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public interface Box2DConstants {

    Vector2 DEFAULT_GRAVITY = new Vector2(0, -9.8f);

    //iterations for updating the World
    int VELOCITY_ITERATIONS = 6; //8
    int POSITION_ITERATIONS = 2; //3

}
