package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 * Base entity for all projectiles in the game.
 */
public abstract class Projectile extends KinematicEntity {

    public Projectile(Level level, Body body) {
        super(level, body);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        level.addToRemove(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

}
