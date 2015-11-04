package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.StaticEntity;
import com.portal2d.game.model.interactions.CollisionFilters;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Portal extends StaticEntity {

    private Portal oppositePortal;
    private PortalColor color;

    private Set<Entity> sentEntities;
    private boolean emitter;

    private Vector2 normal;

    // Velocity when the entity enters the portal.
    public Vector2 entityVelocity;

    public Portal(Level level, Body body, PortalColor color) {
        super(level, body);
        this.color = color;
        this.normal = new Vector2();
        this.sentEntities = new HashSet<Entity>();

        entityVelocity = new Vector2();

        type = EntityType.PORTAL;

        Filter filter = new Filter();
        filter.categoryBits = CollisionFilters.PORTAL_BITS;
        filter.maskBits &= ~CollisionFilters.PORTAL_BITS;
        body.getFixtureList().get(0).setFilterData(filter);

        //System.out.println(Integer.toBinaryString(filter.maskBits));
    }

    /**
     * This is an implicit "send" method.
     */
    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        if(oppositePortal != null && !oppositePortal.isEmitter()) {
            entityVelocity = new Vector2(entity.getBody().getLinearVelocity().x, entity.getBody().getLinearVelocity().y);
            System.out.println("Current velocity1: " + entityVelocity);
            level.addTeleportQueue(entity, oppositePortal);
            setEmitter(true);
            sentEntities.add(entity);
        }
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
        if(oppositePortal != null && !isEmitter()){
            oppositePortal.removeEntityFromList(entity);
            if (oppositePortal.listIsEmpty()) {
                oppositePortal.setEmitter(false);
            }
        }
    }

    //TODO: sacar el hardcodeo
    public void receive(Entity entity) {
        System.out.println("Current velocity3: " + entityVelocity);

        Vector2 oppositePortalNormal = oppositePortal.normal;

        Body entityBody = entity.getBody();

        EntityType entityType = entity.getType();

        // Set the new Position
        //0.1f is the radius of the portal circular shape
        entityBody.setTransform(this.body.getPosition().add(normal.x * (/**0.1f +*/ entityType.getWidth() / 3),
                normal.y * (/**0.1f +*/ entityType.getHeight() / 3)), 0);
        //entityBody.setTransform(this.body.getPosition(), 0);

        //Set the new velocity
        Vector2 currentEntityVelocity = entity.getBody().getLinearVelocity();
        System.out.println(currentEntityVelocity);
        Vector2 currentVelocity = currentEntityVelocity;

        // This is to avoid the player gaining momentum. (cheating)
        currentVelocity.scl(Math.abs(oppositePortalNormal.x), Math.abs(oppositePortalNormal.y));

        Vector2 newVelocity = new Vector2(normal.x * currentVelocity.len(), normal.y * currentVelocity.len());
        entityBody.setLinearVelocity(newVelocity);

        // Apply a minimum impulse so it doesn't get stuck in the walls
        //entityBody.applyLinearImpulse(normal.x, normal.y, body.getPosition().x, body.getPosition().y, true);

    }

    public Vector2 getNormal() {
        return normal;
    }

    public void setNormal(Vector2 normal) {
        this.normal.x = normal.x;
        this.normal.y = normal.y;
    }

    public Portal getOppositePortal(){
        return oppositePortal;
    }

    public void setOppositePortal(Portal portal){
        this.oppositePortal = portal;
    }

    public boolean isEmitter() {
        return emitter;
    }

    public void setEmitter(boolean emitter) {
        this.emitter = emitter;
    }

    public boolean listIsEmpty(){
        return sentEntities.isEmpty();
    }

    public void removeEntityFromList(Entity entity){
        sentEntities.remove(entity);
    }

    public PortalColor getColor(){
        return color;
    }

}
