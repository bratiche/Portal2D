package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 * {@link RayCastCallback} used to create portals.
 */
public class PortalGunRayCast implements RayCastCallback {

    private PortalGun portalGun;
    private PortalColor color;
    private Level level;
    private World world;

    private boolean hit;

    public PortalGunRayCast(PortalGun portalGun, Level level){
        this.portalGun = portalGun;
        this.level = level;
        this.world = level.getWorld();
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {

        Entity entity = (Entity)fixture.getBody().getUserData();
        EntityType type = entity.getType();

        // return 0 terminates the raycast
        if(type == EntityType.SURFACE || type == EntityType.BUTTON) {
            hit = true;
            return 0;
        }

        if(entity.getType() == EntityType.PORTABLE_SURFACE){
//            System.out.println("COLISION CON PORTABLE_SURFACE");
//            System.out.println("Normal: " + normal);
//            System.out.println("Fraccion: " + fraction);
            Vector2 vec = new Vector2(normal.x * PORTAL_RADIUS, normal.y * PORTAL_RADIUS);
            createPortal(new Vector2(point.add(vec)), new Vector2(normal));
            //createPortal(new Vector2(point), new Vector2(normal));

            //entity.getBody().getFixtureList().get(0).setSensor(true);
            hit = true;
            return 0;
        }

        //-1 ignores the fixture and continues
        return -1;
    }

    public void createPortal(Vector2 position, Vector2 portalNormal) {

        Portal portal = portalGun.getPortal(color);

        //System.out.println("position = [" + position + "], portalNormal = [" + portalNormal + "]");

        if(portal == null) {
            BodyDef bdef = new BodyDef();
            bdef.position.set(position);
            bdef.type = BodyDef.BodyType.StaticBody;

            Body body = world.createBody(bdef);

            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(PORTAL_RADIUS);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = circleShape;
            fdef.isSensor = true;

            body.createFixture(fdef);

            portal = new Portal(level, body, color);
            portal.setNormal(portalNormal);

            level.add(portal);

            portalGun.setPortal(portal);
            portalGun.linkPortals();
        }
        else{
            portal.setNormal(portalNormal);
            portal.getBody().setTransform(position, 0);
        }
    }

    public void setPortalColor(PortalColor color) {
        this.color = color;
    }

    public boolean hit() {
        return hit;
    }

    /**
     * Restarts this ray.
     */
    public void restartRay() {
        hit = false;
    }

}
