package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.interactions.RayCast;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 * {@link RayCast} used to create portals.
 */
public class PortalGunRayCast extends RayCast {

    private PortalGun portalGun;
    private PortalColor color;
    private Level level;
    private World world;

    public PortalGunRayCast(PortalGun portalGun, Level level){
        super(level.getWorld());
        this.portalGun = portalGun;
        this.level = level;
        this.world = level.getWorld();
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {

        Entity entity = (Entity)fixture.getBody().getUserData();
        EntityType type = entity.getType();

        // ignore boxes and sensors
        if(type == EntityType.BOX || fixture.isSensor()) {
            return -1;
        }

        if(entity.getType() == EntityType.PORTABLE_SURFACE){
            Vector2 vec = new Vector2(normal.x * PORTAL_RADIUS, normal.y * PORTAL_RADIUS);
            createPortal(new Vector2(point.add(vec)), new Vector2(normal));
            //createPortal(new Vector2(point), new Vector2(normal));
            hit = true;
            return 0;
        }

        hit = true;
        return 0;
    }

    public void createPortal(Vector2 position, Vector2 portalNormal) {

        Portal portal = portalGun.getPortal(color);

        if(portal == null) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(position);
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.angle = portalNormal.angleRad();

            Body body = world.createBody(bodyDef);

            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(PORTAL_RADIUS);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = circleShape;
            fixtureDef.isSensor = true;

            body.createFixture(fixtureDef);

            portal = new Portal(level, body, color);
            portal.setNormal(portalNormal);

            level.add(portal);

            portalGun.setPortal(portal);
            portalGun.linkPortals();
        }
        else{
            portal.setNormal(portalNormal);
            portal.getBody().setTransform(position, portalNormal.angleRad());
        }
    }

    public void setPortalColor(PortalColor color) {
        this.color = color;
    }

}
