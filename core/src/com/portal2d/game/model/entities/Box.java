package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 * An entity that doesn't do anything. Basically dead weight to solve puzzles.
 */
public class Box extends DynamicEntity {

    public Box(Level level, Body body) {
        super(level, body, EntityType.BOX);
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
