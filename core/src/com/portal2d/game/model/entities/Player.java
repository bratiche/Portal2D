package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.model.weapons.Weapon;

/**
 * The main entity in the game, the user controls this entity.
 */
public class Player extends DynamicEntity {

    private Weapon weapon;

    private boolean walking;
    private boolean falling;
    private boolean jumping;
    private boolean facingRight;

    public Player(Level level, Body body) {
        super(level, body);
        type = EntityType.PLAYER;
        weapon = new PortalGun(level, this);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    @Override
    public void beginInteraction(Box box) {

    }

    @Override
    public void update(){
        super.update();
        ((PortalGun) weapon).update();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isWalking() {
        return walking;
    }

    public void setWalking(boolean walking) {
        this.walking = walking;
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

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

}
