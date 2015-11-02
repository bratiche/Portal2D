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

    public Portal(Level level, Body body, PortalColor color) {
        super(level, body);
        this.color = color;
        this.normal = new Vector2();
        this.sentEntities = new HashSet<Entity>();

        type = EntityType.PORTAL;

        Filter filter = new Filter();
        filter.categoryBits = CollisionFilters.PORTAL_BITS;
        filter.maskBits &= ~CollisionFilters.PORTAL_BITS;
        body.getFixtureList().get(0).setFilterData(filter);

        //System.out.println(Integer.toBinaryString(filter.maskBits));
    }

    //TODO: sacar el hardcodeo
    public void receive(Entity entity){

        Vector2 oppositePortalNormal = oppositePortal.normal;

        Body entityBody = entity.getBody();
        Vector2 currentVelocity = entityBody.getLinearVelocity();

        currentVelocity.scl(Math.abs(oppositePortalNormal.x), Math.abs(oppositePortalNormal.y));

        // Set the new Velocity of the entityBody
        Vector2 newVelocity = new Vector2(normal.x * currentVelocity.len(), normal.y * currentVelocity.len());
        System.out.println(newVelocity);
        entityBody.setLinearVelocity(newVelocity);

        // Set the new Position
        //0.1f is the radius of the portal circular shape
        entityBody.setTransform(body.getPosition(), 0);

        // Apply a minimum impulse so it doesn't get stuck in the walls
        entity.getBody().applyLinearImpulse(normal.x * 0.7f, normal.y * 0.7f, body.getPosition().x, body.getPosition().y, true);

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

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        if(oppositePortal != null && !oppositePortal.isEmitter()) {
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
