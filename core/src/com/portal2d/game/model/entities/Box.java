package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class Box extends DynamicEntity {

    public Box(World world, Body body) {
        super(world, body);
    }
}
