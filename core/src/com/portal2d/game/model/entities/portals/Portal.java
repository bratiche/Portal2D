package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.StaticEntity;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.interactions.PortalColor;
import com.portal2d.game.model.level.Level;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Portal extends StaticEntity {

    private Portal oppositePortal;
    private Set<Entity> sentEntities;
    private PortalColor color;
    private boolean emitter;

    private Vector2 normal;

    public Portal(Level level, Body body, PortalColor color) {
        super(level, body);
        this.emitter = false;
        this.sentEntities = new HashSet<Entity>();
        type = EntityType.PORTAL;
        this.color = color;
        this.normal = new Vector2();
    }

    public void setBody(Body body){
        this.body = body;
    }

    //TODO: sacar el hardcodeo
    public void receive(Entity entity){

        Body entityBody = entity.getBody();
        Vector2 currentVelocity = entityBody.getLinearVelocity();

        Vector2 newVelocity = new Vector2(normal.x * currentVelocity.len(), normal.y * currentVelocity.len());

        System.out.println(newVelocity);
        entityBody.setLinearVelocity(newVelocity);

        //0.1f is the radius of the portal circular shape
        entityBody.setTransform(body.getPosition().add(0.1f, 0.1f), 0);

    }

    public Portal getOppositePortal(){
        return oppositePortal;
    }

    @Override
    public void update(){
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

        if(oppositePortal != null && !oppositePortal.isEmitter() && entity.getType() != EntityType.PORTAL_PROJECTILE){
            //System.out.println("Entity type: " + entity.getType());
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
                //System.out.println("Emitter portal is empty");
                oppositePortal.setEmitter(false);
            }
        }
    }

    public void beginInteraction(PortableSurface test){

    }

    public void endInteraction(PortableSurface test){

    }

    public void beginInteraction(PortalProjectile test){
        level.addToRemove(test);
    }

    public PortalColor getColor(){
        return color;
    }

    protected void setNormal(Vector2 normal){
        this.normal.x = normal.x;
        this.normal.y = normal.y;
    }

    public void setOppositePortal(Portal portal){
        this.oppositePortal = portal;
    }

}
