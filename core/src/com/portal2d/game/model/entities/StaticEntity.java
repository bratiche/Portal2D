package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.portal2d.game.model.level.Level;

/**
 * An entity that doesn't move or is affected by any forces.
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Level level, Body body) {
        super(level, body);
        if(body != null)
            body.setType(BodyDef.BodyType.StaticBody);
    }

    @Override
    public void update() {

    }

}
