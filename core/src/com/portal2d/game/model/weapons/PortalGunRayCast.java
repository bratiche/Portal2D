package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.interactions.RayCast;

import static com.portal2d.game.model.ModelConstants.PORTAL_RADIUS;

/**
 * {@link RayCast} used by the {@link PortalGun} to determine if a portal can be created or not.
 */
public class PortalGunRayCast extends RayCast {

    private boolean hitPortableSurface;

    private Vector2 position; // The position to create the portal
    private Vector2 portalNormal; // The normal of the PortableSurface the portal will be on

    public PortalGunRayCast(World world){
        super(world);

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

    public boolean hitPortableSurface() {
        return hitPortableSurface;
    }

    @Override
    public void setRay(Vector2 beginPoint, Vector2 endPoint, float stepLength) {
        super.setRay(beginPoint, endPoint, stepLength);
        hitPortableSurface = false;
    }

    private final Vector2 tmp = new Vector2();

    public Vector2 getPosition() {
        tmp.set(position);
        return tmp;
    }

    private final Vector2 tmp2 = new Vector2();

    public Vector2 getPortalNormal() {
        tmp2.set(portalNormal);
        return tmp2;
    }

}
