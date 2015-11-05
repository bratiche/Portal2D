package com.portal2d.game.model.interactions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;

/**
 * Spring for managing mouse joints.
 */
public class Spring {

    private MouseJoint joint;
    private World world;
    private Body bodyA;

    public Spring(World world) {
        this.world = world;
    }

    public void update(Vector2 position) {
        System.out.println(position);
        joint.setTarget(position.sub(bodyA.getPosition()));
    }

    public void setBodies(Body bodyA, Body bodyB) {
        MouseJointDef jointDef = new MouseJointDef();
        jointDef.bodyA = this.bodyA = bodyA;
        jointDef.bodyB = bodyB;
        jointDef.collideConnected = false;
        jointDef.maxForce = 50;
        //jointDef.length = 0.1f;
        joint = (MouseJoint)world.createJoint(jointDef);
    }

    public void destroy() {
        world.destroyJoint(joint);
    }
}
