package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 * An entity that moves and is affected by all forces applied to it.
 * TODO: create Mob class for movable entities, box is dynamic, but can't walk or jump
 */
public abstract class DynamicEntity extends Entity{

    public DynamicEntity(Level level, Body body) {
        super(level, body);
        body.setType(BodyDef.BodyType.DynamicBody);
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
