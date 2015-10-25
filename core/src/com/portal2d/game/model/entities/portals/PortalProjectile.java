package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.weapons.PortalGun;

/**
 *
 */
public class PortalProjectile extends Projectile {

    private PortalGun portalGun;
    private PortalColor color;

    public enum PortalColor {
        BLUE,
        ORANGE
    }

    public PortalProjectile(Level level, Body body, PortalColor color, PortalGun portalGun) {
        super(level, body);
        this.color = color;
        this.portalGun = portalGun;
    }

    @Override
    public void beginInteraction(PortableSurface surface) {

        //
        // Uso la surface que me pasan para saber donde crear el portal y cual es su direccion de entrada/salida
        //

        //si se puede crear, etc

        BodyDef bodyDef = new BodyDef();
        switch (color) {
            case BLUE:
                if(this.portalGun.getBluePortal() == null){
                    new BluePortal(level, world.createBody(bodyDef), portalGun);
                }
                else {
                    portalGun.getBluePortal().getBody().setTransform(0,0,0);
                }
                break;
            case ORANGE:
                if(portalGun.getOrangePortal() == null) {
                    new OrangePortal(level, world.createBody(bodyDef), portalGun);
                }
                portalGun.getOrangePortal().getBody().setTransform(0,0,0);
                break;
        }
    }

    public PortalColor getColor() {
        return color;
    }

}
