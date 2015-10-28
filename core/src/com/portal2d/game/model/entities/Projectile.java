package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

/**
 * Base entity for all projectiles in the game.
 */
public class Projectile extends KinematicEntity {

    public Projectile(Level level, Body body) {
        super(level, body);
        body.setBullet(true);
        type = EntityType.PROJECTILE;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

}
