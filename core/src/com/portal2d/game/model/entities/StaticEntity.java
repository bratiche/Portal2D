package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.level.Level;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

/**
 * An entity that doesn't move or is affected by any forces.
 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Level level, Vector2 position, EntityType type) {
        super(level, position, type);
        body.setType(StaticBody);
    }

    @Override
    public void update() {

    }

}
