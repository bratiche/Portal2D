package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.entities.StaticEntity;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.weapons.PortalGun;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.model.ModelConstants.PORTAL_RADIUS;

/**
 * Entity that allows to connect two points of the world.
 * Portals are managed by the {@link PortalGun}
 */
public class Portal extends StaticEntity {

    /** The portal that is linked to this */
    private Portal oppositePortal;

    private Vector2 normal;
    private PortalColor color;

    /** Entities to send / Their velocities when they touch this portal */
    private Map<Entity, Vector2> entitiesToSend;

    public Portal(Level level, Vector2 position, PortalColor color, Vector2 normal) {
        super(level, position, EntityType.PORTAL);
        this.color = color;
        this.normal = new Vector2();
        setNormal(normal);

        entitiesToSend = new HashMap<Entity, Vector2>();

        CircleShape shape = new CircleShape();
        shape.setRadius(PORTAL_RADIUS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);

        shape.dispose();
    }

    /** This is an implicit "send" method. */
    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        entitiesToSend.put(entity, new Vector2(entity.getLinearVelocity()));
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);

        if(!isLinked()) {
            entitiesToSend.remove(entity);
        }
    }

    @Override
    public void update() {
        if(isLinked()) {
            sendAll();
        }
    }

    /** Sends all the entities that touched this portal to the {@link #oppositePortal} */
    private void sendAll() {
        for(Map.Entry<Entity, Vector2> entry : entitiesToSend.entrySet()) {
            Entity entity = entry.getKey();
            Vector2 velocity = entry.getValue();
            oppositePortal.receive(entity, velocity);
        }
        entitiesToSend.clear();
    }

    /**
     * Changes the position and velocity of an Entity.
     * @param entity the entity received.
     * @param velocity the previous velocity of the entity.
     */
    private void receive(Entity entity, Vector2 velocity) {

        EntityType entityType = entity.getType();

        // Set the new Position
        Vector2 position = this.getPosition().add(normal.x * (PORTAL_RADIUS + 0.02f + entityType.getWidth() / 2),
                normal.y * (PORTAL_RADIUS + 0.02f + entityType.getHeight() / 2));

        entity.setPosition(position);

        // Calculate the new velocity
        Vector2 currentVelocity = new Vector2(velocity);

        // This is to avoid the player gaining momentum by increasing it's velocity. (cheating)
        currentVelocity.scl(Math.abs(oppositePortal.normal.x), Math.abs(oppositePortal.normal.y));

        Vector2 newVelocity = new Vector2(normal.x * currentVelocity.len(), normal.y * currentVelocity.len());

        // Cap minimum velocity
        if(Math.abs(newVelocity.x) < normal.x * 2.0f || Math.abs(newVelocity.y) < normal.y * 2.0f) {
            newVelocity.set(normal.x * 2.0f, normal.y * 2.0f);
        }

        // Set the new velocity of the entity
        entity.setLinearVelocity(newVelocity);
    }

    public Vector2 getNormal() {
        return normal;
    }

    public void setNormal(Vector2 normal) {
        this.normal.x = normal.x;
        this.normal.y = normal.y;
        setAngle(normal.angleRad());
    }

    /** Links this portal with the specified portal */
    public void setOppositePortal(Portal portal){
        if(portal == null) {
            throw new NullPointerException("Portal cannot be null");
        }
        this.oppositePortal = portal;
    }

    public PortalColor getColor(){
        return color;
    }

    /** Returns true if this portal is linked to another portal. */
    public boolean isLinked() {
        return oppositePortal != null;
    }

    /** Returns true if the list of entities to send is not empty. */
    public boolean hasEntitiesToSend() {
        return !entitiesToSend.isEmpty();
    }

}
