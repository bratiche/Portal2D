package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.interactions.RayCast;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.PORTAL_RADIUS;

/**
 * {@link RayCast} used to create portals.
 */
public class PortalGunRayCast extends RayCast {

    private Level level;
    private PortalGun portalGun;

    private boolean hitPortableSurface;

    private Vector2 position; // The position to create the portal
    private Vector2 portalNormal; // The normal of the PortableSurface the portal will be on

    public PortalGunRayCast(PortalGun portalGun, Level level){
        super(level.getWorld());
        this.portalGun = portalGun;
        this.level = level;

        position = new Vector2();
        portalNormal = new Vector2();
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {

        Entity entity = (Entity)fixture.getBody().getUserData();
        EntityType type = entity.getType();

        // ignore boxes and sensors
        if(type == EntityType.BOX || fixture.isSensor()) {
            return -1;
        }

        endPoint.set(point);
        hit = true;

        if(entity.getType() == EntityType.PORTABLE_SURFACE) {
            Vector2 vec = new Vector2(normal.x * PORTAL_RADIUS, normal.y * PORTAL_RADIUS);
            position.set(point.add(vec));
            portalNormal.set(normal);
            hitPortableSurface = true;
        }
        else {
            hitPortableSurface = false;
        }

        return fraction; // terminates the ray cast on the closest hit
    }

    /**
     * Creates a {@link Portal}, or changes it's position and normal if it is already created.
     */
    protected void createPortal(PortalColor color) {
        Portal portal = portalGun.getPortal(color);

        if(portal == null) {
            portal = new Portal(level, position, color, portalNormal);

            level.add(portal);
            portalGun.setPortal(portal);
            portalGun.linkPortals();
        }
        else {
            portal.setPosition(position);
            portal.setNormal(portalNormal);
        }
    }

    public boolean hitPortableSurface() {
        return hitPortableSurface;
    }

    @Override
    public void setRay(Vector2 beginPoint, Vector2 endPoint, float stepLength) {
        super.setRay(beginPoint, endPoint, stepLength);
        hitPortableSurface = false;
    }
}
