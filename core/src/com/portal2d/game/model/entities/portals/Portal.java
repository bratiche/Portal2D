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
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class Portal extends StaticEntity {

    private Portal oppositePortal;
    private PortalColor color;

    private Set<Entity> sentEntities;
    private Map<Entity, Vector2> sentEntitiesWithVelocity;
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

    //TODO: sacar el hardcodeo
    public void receive(Entity entity){
        System.out.println("Current velocity3: " + entityVelocity);
        Vector2 oppositePortalNormal = oppositePortal.normal;

        Body entityBody = entity.getBody();
        EntityType entityType = entity.getType();

        Vector2 currentEntityVelocity = entity.getBody().getLinearVelocity();
        System.out.println(currentEntityVelocity);
        Vector2 currentVelocity = currentEntityVelocity;


        // This is to avoid the player gaining momentum. (cheating)
        currentVelocity.scl(Math.abs(oppositePortalNormal.x), Math.abs(oppositePortalNormal.y));

        // Set the new Velocity of the entityBody
        Vector2 newVelocity = new Vector2(normal.x * currentVelocity.len(), normal.y * currentVelocity.len());
        entityBody.setLinearVelocity(newVelocity);

        // Set the new Position
        //0.1f is the radius of the portal circular shape
        entityBody.setTransform(this.body.getPosition().add(normal.x * (0.1f + entityType.getWidth() / 2),
                normal.y * (0.1f + entityType.getHeight() / 2)), 0);

        //entityBody.setTransform(this.body.getPosition(), 0);

        // Apply a minimum impulse so it doesn't get stuck in the walls
        entityBody.applyLinearImpulse(normal.x, normal.y, body.getPosition().x, body.getPosition().y, true);

    }

    public Portal getOppositePortal(){
        return oppositePortal;
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

    public PortalColor getColor(){
        return color;
    }

    public void setNormal(Vector2 normal){
        this.normal.x = normal.x;
        this.normal.y = normal.y;
    }

    public void setOppositePortal(Portal portal){
        this.oppositePortal = portal;
    }

}
