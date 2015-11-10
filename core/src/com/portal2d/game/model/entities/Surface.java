package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 * Represents a wall or the floor, it's an obstacle that cannot be crossed by.
 */
public class Surface extends StaticEntity {

    public Surface(Level level, Body body) {
        super(level, body, EntityType.SURFACE); // The type is overridden by PortableSurface
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
