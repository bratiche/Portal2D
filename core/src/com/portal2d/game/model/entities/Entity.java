package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.entities.portals.BluePortal;
import com.portal2d.game.model.entities.portals.OrangePortal;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.interactions.EntityType;

/**
 * Base class for all game entities.
 */
public abstract class Entity {

    protected Body body;
    protected Level level;
    protected World world;

    protected EntityType type;

    protected Entity(Level level, Body body) {
        this.level = level;
        this.world = level.getWorld();
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public EntityType getType() {
        return type;
    }

    public abstract void beginInteraction(Entity entity);
    public abstract void endInteraction(Entity entity);

    // begin interactions
    public void beginInteraction(Box box) {
    }

    public void beginInteraction(Player player) {
    }

    public void beginInteraction(Exit exit) {
    }

    public void beginInteraction(Button button) {
    }

    public void beginInteraction(Portal portal) {
    }

    public void beginInteraction(BluePortal portal) {
    }

    public void beginInteraction(OrangePortal portal) {
    }

    public void beginInteraction(Gate gate) {
    }


    // end interactions
    public void endInteraction(Box box) {
    }

    public void endInteraction(Player player) {
    }

    public void endInteraction(Exit exit) {
    }

    public void endInteraction(Button button) {
    }

    public void endInteraction(Portal portal) {
    }

    public void endInteraction(BluePortal portal) {
    }

    public void endInteraction(OrangePortal portal) {
    }

    public void endInteraction(Gate gate) {
    }

    public void update() {

    }
}
