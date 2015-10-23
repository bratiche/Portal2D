package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * An entity that doesn't move or is affected by any forces.
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(World world, Body body) {
        super(world, body);
        body.setType(BodyDef.BodyType.StaticBody);
    }

}
