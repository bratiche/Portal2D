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
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        if(getOrangePortal() != null && !getOrangePortal().isEmitter()){
            level.addTeleportQueue(entity, getOrangePortal());
            setEmitter(true);
        }
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
        if(getOrangePortal() != null && !isEmitter()) {
            getOrangePortal().setEmitter(false);
        }
    }

}
