package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.*;
import static com.portal2d.game.model.ModelConstants.TERMINAL_VELOCITY;

/**
 * An entity that moves and is affected by all forces applied to it.
 */
public abstract class DynamicEntity extends Entity{

    public DynamicEntity(Level level, Body body, EntityType type) {
        super(level, body, type);
        body.setType(DynamicBody);
    }

    @Override
    public void update() {
        body.setAwake(true);

        //cap terminal velocity
        Vector2 velocity = body.getLinearVelocity();

        if(Math.abs(velocity.y) > TERMINAL_VELOCITY) {
            velocity.y = Math.signum(velocity.y) * TERMINAL_VELOCITY;
            body.setLinearVelocity(velocity.x, velocity.y);
        }
    }

}
