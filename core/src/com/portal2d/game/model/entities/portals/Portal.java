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
    protected boolean emitter;

    public Portal(Level level, Body body, PortalGun portalGun) {
        super(level, body);
        this.portalGun = portalGun;
        this.emitter = false;
        type = EntityType.PORTAL;
    }

    public void receive(Entity entity){
        entity.getBody().setTransform(body.getPosition(), 0);
    }

    @Override
    public void update(){
    }

    public BluePortal getBluePortal() {
        return portalGun.getBluePortal();
    }

    public OrangePortal getOrangePortal() {
        return portalGun.getOrangePortal();
    }

    public boolean isEmitter() {
        return emitter;
    }

    public void setEmitter(boolean emitter) {
        this.emitter = emitter;
    }

}
