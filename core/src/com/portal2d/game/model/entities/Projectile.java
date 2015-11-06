package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

/**
 * Base entity for all projectiles in the game.
 */
public abstract class Projectile extends DynamicEntity {

    // Constant velocity
    protected Vector2 velocity;

    public Projectile(Level level, Body body, Vector2 velocity) {
        super(level, body);
        this.velocity = velocity;
        body.setBullet(true);
        body.getFixtureList().get(0).setSensor(true);
        body.setLinearVelocity(velocity);
    }

    @Override
    public void update() {
        body.setLinearVelocity(velocity);
    }

}
