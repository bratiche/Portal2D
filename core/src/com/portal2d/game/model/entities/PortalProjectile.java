package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class PortalProjectile extends Projectile {
    private PortalGun portalGun;
    private PortalColor color;

    // tengo que tener el world para crear projectiles, todas las entidades tienen que tener una referencia al world
    public PortalProjectile(World world, Body body, PortalColor color, PortalGun portalGun) {
        super(world, body);
        this.color = color;
        this.portalGun = portalGun;
    }

    public enum PortalColor {
        BLUE,
        ORANGE
    }

    public void crash() {


        //si se puede crear, etc

        BodyDef bodyDef = new BodyDef();
        switch (color) {
            case BLUE:
                if(this.portalGun.getBluePortal() == null){
                    new BluePortal(world, world.createBody(bodyDef), portalGun);
                }
                else {
                    portalGun.getBluePortal().getBody().setTransform(0,0,0);
                }
                break;
            case ORANGE:
                if(portalGun.getOrangePortal() == null) {
                    new OrangePortal(world, world.createBody(bodyDef), portalGun);
                }
                portalGun.getOrangePortal().getBody().setTransform(0,0,0);
                break;
        }
    }
}
