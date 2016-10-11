package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.weapons.PortalGun;

import static com.portal2d.game.model.ModelConstants.PLAYER_HEIGHT;
import static com.portal2d.game.model.ModelConstants.PLAYER_WIDTH;
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

    public Player(Level level, Vector2 position) {
        super(level, position, EntityType.PLAYER);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH / 2, PLAYER_HEIGHT / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        body.setFixedRotation(true);

        shape.dispose();

        portalGun = new PortalGun(level, this);
        state = STANDING;
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

    public void setPortalGun(PortalGun portalGun) {
        this.portalGun = portalGun;
    }

    public boolean hasWeapon() {
        return portalGun != null;
    }

    public PlayerState getState() {
        return state;
    }

}
