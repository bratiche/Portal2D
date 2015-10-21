package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class BluePortal extends Portal {
    public BluePortal(World world, Body body, PortalGun portalGun) {
        super(world, body, portalGun);
    }

    public void send(Entity entity) {
        super.send(entity, getOrangePortal());
    }
}
