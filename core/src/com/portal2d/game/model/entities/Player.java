package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.weapons.PortalGun;

import static com.portal2d.game.model.entities.Player.PlayerState.*;

/**
 * The main entity in the game, the user controls this entity.
 */
public class Player extends DynamicEntity {

    private PortalGun portalGun;

    public enum PlayerState {
        WALKING,
        JUMPING,
        FALLING,
        STANDING,
        DEAD
    }

    private PlayerState state;
    private boolean facingRight;

    public Player(Level level, Body body) {
        super(level, body, EntityType.PLAYER);
        portalGun = new PortalGun(level, this);
        state = PlayerState.STANDING;
        facingRight = true;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    public PortalGun getPortalGun() {
        return portalGun;
    }

    public boolean isWalking() {
        return state == WALKING;
    }

    public boolean isJumping() {
        return state == JUMPING;
    }

    public boolean isFalling() {
        return state == FALLING;
    }

    public boolean isStanding() {
        return state == STANDING;
    }

    public boolean isDead() {
        return state == DEAD;
    }

    public void die() {
        setState(DEAD);
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

}
