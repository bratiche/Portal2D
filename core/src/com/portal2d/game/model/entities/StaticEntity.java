package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Body body) {
        super(body);
        body.setType(BodyDef.BodyType.StaticBody);
    }

}
