package com.portal2d.game.model.interactions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.*;

/**
 * Spring for managing distance joints between two bodies.
 */
public class Spring {

    private DistanceJoint joint;
    private World world;

    private Body bodyA;
    private Body bodyB;

    private float maxLength;

    public Spring(World world, float maxLength) {
        this.world = world;
        this.maxLength = maxLength;
    }

    public void update(Vector2 position) {

        float distance = new Vector2(position).sub(bodyA.getPosition()).len();

        Vector2 direction = new Vector2(position);
        direction.sub(bodyA.getPosition());
        direction.nor();

        // Set joint length
        if(distance > maxLength) {
            distance = maxLength;
        }

        joint.setLength(distance);
        bodyB.setTransform(bodyA.getPosition().add(direction.scl(distance)), 0);

    }

    /**
     * Creates a new distance joint between the two bodies.
     * @param bodyA main body
     * @param bodyB secondary body
     */
    public void setBodies(Body bodyA, Body bodyB) {
        DistanceJointDef jointDef = new DistanceJointDef();
        jointDef.bodyA = this.bodyA = bodyA;
        jointDef.bodyB = this.bodyB = bodyB;
        jointDef.collideConnected = false;
        jointDef.length = maxLength;
        joint = (DistanceJoint)world.createJoint(jointDef);
    }

    public void destroy() {
        if(joint != null) {
            world.destroyJoint(joint);
        }
    }
}
