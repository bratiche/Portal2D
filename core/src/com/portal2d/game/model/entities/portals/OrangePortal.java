package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.weapons.PortalGun;

/**
 *
 */
public class OrangePortal extends Portal {

    public OrangePortal(Level level, Body body, PortalGun portalGun) {
        super(level, body, portalGun);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        if(getBluePortal() != null && !getBluePortal().isEmitter()){
            level.addTeleportQueue(entity, getBluePortal());
            setEmitter(true);
        }
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
        if(getBluePortal() != null && !isEmitter()){
            getBluePortal().setEmitter(false);
        }
    }

}
