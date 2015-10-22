package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.interactions.EntityType;

/**
 *
 */
public abstract class Entity {

    protected Body body;
    protected World world;

    protected EntityType type;

    protected Entity(World world, Body body) {
        this.world = world;
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
    public EntityType getType() {
        return type;
    }

    public void interact(Entity entity) {
        entity.type.interact(this, entity);
    }

    public abstract void interact (Box box);
    public abstract void interact (Player player);
    public abstract void interact (Exit exit);
    public abstract void interact (Button button);
    public abstract void interact (Tile tile);
}
