package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.level.Level;
import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.KinematicBody;

/**
 *
 */
public abstract class KinematicEntity extends Entity {

    protected KinematicEntity(Level level, Vector2 position, EntityType type) {
        super(level, position, type);
        body.setType(KinematicBody);
    }

    @Override
    public void update() {

    }

}
