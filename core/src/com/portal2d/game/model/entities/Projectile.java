package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 * Base entity for all projectiles in the game.
 */
public abstract class Projectile extends DynamicEntity {

    // Constant velocity
    protected Vector2 velocity;

    public Projectile(Level level, Body body, Vector2 velocity, EntityType type) {
        super(level, body, type);
        this.velocity = velocity;
        body.setBullet(true);
        this.setSensor(true);
    }

    @Override
    public void update() {
        setLinearVelocity(velocity);
    }

}
