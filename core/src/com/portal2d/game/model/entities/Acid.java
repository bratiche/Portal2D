package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 * Entity that destroys anything that touches it.
 */
public class Acid extends StaticEntity {

    public Acid(Level level, Body body) {
        super(level, body, EntityType.ACID);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        level.addToRemove(entity);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    @Override
    public void beginInteraction(Player player) {
        player.die();
    }
}
