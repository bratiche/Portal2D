package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.entities.enemies.Turret;
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

    protected Entity(Level level, BodyDef bodyDef, EntityType type) {
        this(level, level.getWorld().createBody(bodyDef), type);
    }

    /** Constructs an Entity with the given body */
    protected Entity(Level level, Body body, EntityType type) {
        this.type = type;
        this.body = body;
        this.level = level;
        this.world = level.getWorld();
        body.setUserData(this);
    }

    /** Constructs an entity with no shape at the specified position */
    protected Entity(Level level, Vector2 position, EntityType type) {
        this.type = type;
        this.level = level;
        this.world = level.getWorld();
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        body = world.createBody(bodyDef);
    }

    /** Called by the level each frame. */
    public abstract void update();

    public abstract void beginInteraction(Entity entity);

    public abstract void endInteraction(Entity entity);

    ////////////////////////////////////////////////// TODO use
    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void setPosition(Vector2 position) {
        body.setTransform(position, body.getAngle());
    }

    public float getAngle() {
        return body.getAngle();
    }

    public void setAngle(float radians) {
        body.setTransform(body.getPosition(), radians);
    }

    public void setTransform(Vector2 position, float radians) {
        body.setTransform(position, radians);
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    public void setLinearVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public void setLinearVelocity(float x, float y) {
        body.setLinearVelocity(x, y);
    }

    public void setSensor(boolean sensor) {
        body.getFixtureList().get(0).setSensor(sensor);
    }
    ////////////////////////////////////////////////// TODO use


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

    public void beginInteraction(Turret turret) {
    }

    /**
     * Implemented this way so that the {@link Bullet} is removed when touching any entity by default.
     * If a specific Entity should not be destroyed by a bullet, it has to override this method.
     */
    public void beginInteraction(Bullet bullet) {
        level.addToRemove(bullet);
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

    public void endInteraction(Bullet bullet) {
    }

    public void endInteraction(Turret turret) {
    }

}
