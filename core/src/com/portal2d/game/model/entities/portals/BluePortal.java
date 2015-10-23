package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.weapons.PortalGun;

/**
 *
 */
public class BluePortal extends Portal {

    public BluePortal(Level level, Body body, PortalGun portalGun) {
        super(level, body, portalGun);
        body.setUserData(this);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        if(getOrangePortal() != null && getOrangePortal().canBeUsed()){
            level.addTeleportQueue(entity, getOrangePortal());
            setTimer(entity);
        }
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);

    }



}
