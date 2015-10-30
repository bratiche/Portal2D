package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.Definitions;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.interactions.PortalColor;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.weapons.PortalGun;

/**
 *
 */
public class PortalProjectile extends Projectile {

    private PortalGun portalGun;
    private Vector2 portalNormal;

    private PortalColor color;

    public PortalProjectile(Level level, Body body, PortalColor color, PortalGun portalGun, Vector2 velocity) {
        super(level, body, velocity);
        this.color = color;
        this.portalGun = portalGun;
        type = EntityType.PORTAL_PROJECTILE;
    }

//    @Override
//    public void beginInteraction(PortableSurface surface) {
//
//        //TODO create portal from surface
//
//        BodyDef bodyDef = new BodyDef();
//        switch (color) {
//            case BLUE:
//                if(this.portalGun.getBluePortal() == null){
//                    new BluePortal(level, world.createBody(bodyDef), portalGun);
//                }
//                else {
//                    portalGun.getBluePortal().getBody().setTransform(0,0,0);
//                }
//                break;
//            case ORANGE:
//                if(portalGun.getOrangePortal() == null) {
//                    new OrangePortal(level, world.createBody(bodyDef), portalGun);
//                }
//                portalGun.getOrangePortal().getBody().setTransform(0,0,0);
//                break;
//        }
//    }

    public void createPortal() {

        Portal portal = portalGun.getPortal(color);


        if(portal == null) {
            BodyDef bdef = new BodyDef();
            bdef.position.set(body.getPosition());
            bdef.type = BodyDef.BodyType.StaticBody;


            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(0.1f);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = circleShape;
            fdef.isSensor = true;

            portal = new Portal(level, null, color);
            portal.setNormal(portalNormal);

            System.out.println();

            portalGun.setPortal(portal);

            Definitions definitions = new Definitions(bdef,fdef,circleShape);

            level.addToCreationQueue(portal,definitions);

            portalGun.linkPortals();

        }
        else{
            level.addToMoveQueue(portal,body.getPosition());
            portal.setNormal(portalNormal);
        }




    }

    public PortalColor getColor() {
        return color;
    }

    public void setPortalNormal(Vector2 normal) {
        portalNormal = normal;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }
}
