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
        body.setUserData(this);
    }


    //TODO: Tal vez overridear interacciones con entidades especificas para settear timers separados;
    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        if(getBluePortal() != null && getBluePortal().canBeUsed()){
            level.addTeleportQueue(entity, getBluePortal());
            setTimer(entity);
        }

    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }
}
