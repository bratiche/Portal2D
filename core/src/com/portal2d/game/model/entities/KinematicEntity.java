package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.portal2d.game.model.level.Level;

/**
 * An entity that has a constant velocity.
 */
public abstract class KinematicEntity extends Entity {

    protected KinematicEntity(Level level, Body body) {
        super(level, body);
        body.setType(BodyDef.BodyType.KinematicBody);
    }
}
