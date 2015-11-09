package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.KinematicBody;

/**
 * An entity that has infinite mass and constant velocity. e.g moving platforms
 */
@SuppressWarnings("unused")
public abstract class KinematicEntity extends Entity {

    protected KinematicEntity(Level level, Body body, EntityType type) {
        super(level, body, type);
        body.setType(KinematicBody);
    }

}
