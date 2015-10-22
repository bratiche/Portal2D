package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.weapons.PortalGun;

/**
 *
 */
public class OrangePortal extends Portal {

    public OrangePortal(World world, Body body, PortalGun portalGun) {
        super(world, body, portalGun);
    }

    public void send(Entity entity){
        super.send(entity, getBluePortal());
    }
}
