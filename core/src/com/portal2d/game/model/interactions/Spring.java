package com.portal2d.game.model.interactions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.*;

/**
 * Spring for managing mouse joints.
 */
public class Spring {

    private DistanceJoint joint;
    private World world;
    private Body bodyA; //the player
    private Body bodyB; //the box

    private float max_lenght = 1.0f;

    public Spring(World world) {
        this.world = world;
    }

    public void update(Vector2 position) {

        float distance = new Vector2(position).sub(bodyA.getPosition()).len();
        System.out.println(distance);

        Vector2 direction = new Vector2(position);
        direction.sub(bodyA.getPosition());
        direction.nor();

        // Set distance joint
        if(distance > max_lenght) {
            distance = max_lenght;
        }

        joint.setLength(distance);
        bodyB.setTransform(bodyA.getPosition().add(direction.scl(distance)), 0);

    }

    public void setBodies(Body bodyA, Body bodyB) {
        DistanceJointDef jointDef = new DistanceJointDef();
        jointDef.bodyA = this.bodyA = bodyA;
        jointDef.bodyB = this.bodyB = bodyB;
        bodyB.setGravityScale(0);
        jointDef.collideConnected = false;
        jointDef.length = max_lenght;
        joint = (DistanceJoint)world.createJoint(jointDef);
    }

    public void destroy() {
        world.destroyJoint(joint);
    }
}
