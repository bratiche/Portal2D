package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Belén on 21/10/2015.
 */
public class OrangePortal extends Portal {

    public OrangePortal(World world, Body body, PortalGun portalGun) {
        super(world, body, portalGun);
    }

    public void send(Entity entity){
        super.send(entity, getBluePortal());
    }
}
