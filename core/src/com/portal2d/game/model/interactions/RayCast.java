package com.portal2d.game.model.interactions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.enemies.TurretRayCast;
import com.portal2d.game.model.weapons.PortalGunRayCast;

/**
 * Base class for all {@link RayCastCallback}s
 * @see PortalGunRayCast
 * @see TurretRayCast
 */
public abstract class RayCast implements RayCastCallback {

    protected Vector2 beginPoint;
    protected Vector2 endPoint;
    protected float stepLength;
    protected boolean hit;

    protected World world;

    public RayCast(World world) {
        this.world = world;
        beginPoint = new Vector2();
        endPoint = new Vector2();
    }

    public void setRay(Vector2 beginPoint, Vector2 endPoint, float stepLength) {
        this.beginPoint.set(beginPoint);
        this.endPoint.set(endPoint);
        this.stepLength = stepLength;
        hit = false;
    }

    /**
     * Ray-cast the world in constant steps specified in {@link #setRay(Vector2, Vector2, float)}
     * @see World#rayCast(RayCastCallback, Vector2, Vector2)
     */
    public void process() {
        Vector2 step = new Vector2(endPoint);
        step.sub(beginPoint);
        step.nor();
        step.scl(stepLength);

        Vector2 endPoint = new Vector2(beginPoint);
        endPoint.add(step);

        while(!hit) {
            world.rayCast(this, beginPoint, endPoint);
            endPoint.add(step);
        }
    }

    @Override
    public abstract float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction);


    //TESTEO

//    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
//        if(fixture.isSensor()) {
//            return -1;
//        }
//        hit = true;
//        endPoint.set(point);
//        return fraction;
//    }
//
//    public Vector2 getBeginPoint() {
//        return beginPoint;
//    }
//
//    public Vector2 getEndPoint() {
//        return endPoint;
//    }
}
