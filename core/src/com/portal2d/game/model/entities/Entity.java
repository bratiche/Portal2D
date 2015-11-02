package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.portals.*;
import com.portal2d.game.model.level.Level;
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
        body.setUserData(this);
    }

    /**
     * Called by the level each frame.
     */
    public abstract void update();

    /**
     * If we don't make these two methods abstract we get StackOverflowError. (infinite recursion)
     * TODO: remove javadoc
     */
    public abstract void beginInteraction(Entity entity);

    public abstract void endInteraction(Entity entity);

    public Body getBody() {
        return body;
    }

    public EntityType getType() {
        return type;
    }

    // Begin interactions
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

    public void beginInteraction(Gate gate) {
    }

    public void beginInteraction(Surface surface) {
    }

    public void beginInteraction(PortableSurface surface) {
    }

    /**
     * Implemented this way so that the {@link Missile} is removed when touching any entity by default.
     * If a specific Entity should not be destroyed by a missile, it has to override this method.
     */
    public void beginInteraction(Missile missile) {
        level.addToRemove(missile);
    }

    // End interactions
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

    public void endInteraction(Gate gate) {
    }

    public void endInteraction(Surface surface) {
    }

    public void endInteraction(PortableSurface surface) {
    }

    public void endInteraction(Missile missile) {
    }

}
