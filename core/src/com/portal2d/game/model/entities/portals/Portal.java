package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.entities.StaticEntity;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.weapons.PortalGun;

import java.util.HashSet;
import java.util.Set;

import static com.portal2d.game.model.ModelConstants.PORTAL_RADIUS;

/**
 * Entity that allows to connect two points of the world.
 * Portals are managed by the {@link PortalGun}
 */
public class Portal extends StaticEntity {

    /** The portal that is linked to this */
    private Portal oppositePortal;
    private PortalColor color;

    private Vector2 normal;

    // Velocity when the entity enters the portal.
    private Vector2 entityVelocity;

    // This set is used to store entities when the portals are not linked
    private Set<Entity> entitiesToSend;

    public Portal(Level level, Vector2 position, PortalColor color, Vector2 normal) {
        super(level, position, EntityType.PORTAL);
        this.color = color;
        this.normal = new Vector2();
        setNormal(normal);

        entityVelocity = new Vector2();
        entitiesToSend = new HashSet<Entity>();

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
        if(isLinked()) {
            entityVelocity.set(entity.getLinearVelocity());
            level.addTeleportQueue(entity, oppositePortal);
        }
        else {
            entitiesToSend.add(entity); // The entity will be sent when this portal gets linked to another portal
        }
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);

        if(!isLinked()) {
            entitiesToSend.remove(entity);
        }
    }

    /**
     * Changes the position and velocity of an Entity.
     * @param entity the entity received.
     */
    public void receive(Entity entity) {

        EntityType entityType = entity.getType();

        // Set the new Position
        Vector2 position = this.getPosition().add(normal.x * (PORTAL_RADIUS + 0.02f + entityType.getWidth() / 2),
                normal.y * (PORTAL_RADIUS + 0.02f + entityType.getHeight() / 2));

        entity.setPosition(position);

        // Calculate the new velocity
        Vector2 currentVelocity = oppositePortal.entityVelocity;

        // This is to avoid the player gaining momentum by increasing it's velocity. (cheating)
        currentVelocity.scl(Math.abs(oppositePortal.normal.x), Math.abs(oppositePortal.normal.y));

        Vector2 newVelocity = new Vector2(normal.x * currentVelocity.len(), normal.y * currentVelocity.len());

        // Cap minimum velocity
        if(Math.abs(newVelocity.x) < normal.x * 2f || Math.abs(newVelocity.y) < normal.y * 2f) {
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

    public void setOppositePortal(Portal portal){
        this.oppositePortal = portal;

        // Sends all the entities that were touching this portal before it was linked
        for(Entity entity : entitiesToSend) {
            level.addTeleportQueue(entity, oppositePortal);
        }

        entitiesToSend.clear();
    }

    public PortalColor getColor(){
        return color;
    }

    /** Returns true if this portal is linked to another portal. */
    public boolean isLinked() {
        return oppositePortal != null;
    }

}
