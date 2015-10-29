package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

/**
 * TODO: make abstract?
 */
public class Surface extends StaticEntity {

    protected Vector2 normal;

    public Surface(Level level, Body body) {
        super(level, body);
        type = EntityType.SURFACE;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    public Vector2 getNormal() {
        return normal;
    }

    public void setNormal(Vector2 normal) {
        this.normal = normal;
    }
}
