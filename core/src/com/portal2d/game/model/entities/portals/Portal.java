package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.weapons.PortalGun;

/**
 *
 */
public abstract class Portal extends Entity {

    private PortalGun portalGun;

     public Portal(World world, Body body, PortalGun portalGun) {
        super(world, body);
        this.portalGun = portalGun;
    }

    /**
     *
     * @param entity
     * @param destination
     */
    public void send(Entity entity, Portal destination) {
        entity.getBody().setTransform(destination.getBody().getPosition(), 0); //varia segun donde apunte el portal el 0
    }

    public BluePortal getBluePortal() {
        return portalGun.getBluePortal();
    }

    public OrangePortal getOrangePortal() {
        return portalGun.getOrangePortal();
    }
}
