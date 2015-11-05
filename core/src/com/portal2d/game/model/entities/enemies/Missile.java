package com.portal2d.game.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class Missile extends Projectile {

    public Missile(Level level, Body body, Vector2 velocity) {
        super(level, body, velocity);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    /**
     * Overridden so missiles don't destroy each other on collision.
     */
    @Override
    public void beginInteraction(Missile projectile) {

    }

}
