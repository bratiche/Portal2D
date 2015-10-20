package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * TODO: create Mob class for movable entities, box is dynamic, but can't walk or jump
 */
public abstract class DynamicEntity extends Entity{

    protected boolean walking;
    protected boolean jumping;
    protected boolean facingRight;

    public DynamicEntity(Body body) {
        super(body);
        body.setType(BodyDef.BodyType.DynamicBody);
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

}
