package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.weapons.PortalGun;

/**
 *
 */
public abstract class Portal extends StaticEntity {

    private PortalGun portalGun;
    protected Timer timer;

    public Portal(Level level, Body body, PortalGun portalGun) {
        super(level, body);
        this.portalGun = portalGun;
        type = EntityType.PORTAL;
        timer = new Timer(0,0);
    }

    public void receive(Entity entity){
        if(!canBeUsed())
            return;
        setTimer(entity);
        entity.getBody().setTransform(body.getPosition(), 0);
    }

    @Override
    public void update(){
        timer.tick();
    }

    public BluePortal getBluePortal() {
        return portalGun.getBluePortal();
    }

    public OrangePortal getOrangePortal() {
        return portalGun.getOrangePortal();
    }

    public boolean canBeUsed(){
        if(timer.getTime() < timer.getCooldown())
            return false;
        return true;
    }

    //TODO: Overload setTimer / use the same timer for all entities.
    protected void setTimer(Entity entity){
        timer = new Timer(1/60.0f, 5);
    }

}
