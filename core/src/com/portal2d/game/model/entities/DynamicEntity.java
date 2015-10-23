package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.portal2d.game.model.level.Level;

/**
 * An entity that moves and is affected by all forces applied to it.
 * TODO: create Mob class for movable entities, box is dynamic, but can't walk or jump
 */
public abstract class DynamicEntity extends Entity{

    protected boolean walking;
    protected boolean jumping;
    protected boolean facingRight;

    public DynamicEntity(Level level, Body body) {
        super(level, body);
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
