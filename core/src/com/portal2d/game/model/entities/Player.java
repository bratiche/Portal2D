package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.model.weapons.Weapon;

import static com.portal2d.game.model.ModelConstants.GRAB_RADIUS;
import static com.portal2d.game.model.interactions.CollisionFilters.BOX_BITS;
import static com.portal2d.game.model.interactions.CollisionFilters.PLAYER_BITS;
import static com.portal2d.game.model.interactions.CollisionFilters.PORTAL_BITS;

/**
 * The main entity in the game, the user controls this entity.
 * TODO: bit-masking for drag and drop entities
 */
public class Player extends DynamicEntity {

    private Weapon weapon;

    private boolean walking;
    private boolean falling;
    private boolean jumping;
    private boolean standing;
    private boolean facingRight;

    private Entity grabbedEntity;

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
    public void update() {
        super.update();
        if(grabbedEntity != null) {
            float entityWith = grabbedEntity.getType().getWidth();
            float entityHeight = grabbedEntity.getType().getHeight();
            float playerWith = type.getWidth();
            float playerHeight = type.getHeight();

            //grabbedEntity.getBody().setTransform(this.body.getPosition().add(playerWith / 2 + entityWith / 2, playerHeight / 2 + entityHeight / 2), 0);
            grabbedEntity.getBody().setTransform(this.body.getPosition(), 0);
        }
    }

    //TODO the PortalGun should be the one who grabs and drops the entities
    //TODO create a joint between the player and the entity to grab
    public void grab(Entity entity) {

        //((PortalGun)weapon).grabEntity(box);

        grabbedEntity = entity;

        // Make the box not collide with the player and portals

        Fixture fixture = entity.getBody().getFixtureList().get(0);
        Filter filter = new Filter();
        filter.categoryBits = BOX_BITS;
        filter.maskBits &= ~PLAYER_BITS;
        filter.maskBits &= ~PORTAL_BITS;

        fixture.setFilterData(filter);

        Fixture playerFixture = body.getFixtureList().get(0);
        Filter playerFilter = new Filter();
        playerFilter.categoryBits = PLAYER_BITS;
        playerFilter.maskBits &= ~BOX_BITS;

        playerFixture.setFilterData(playerFilter);
    }

    /**
     * Drops the entity that was grabbed before.
     */
    public void drop() {

        //((PortalGun)weapon).dropEntity();
        Fixture fixture = grabbedEntity.getBody().getFixtureList().get(0);
        grabbedEntity.getBody().setLinearVelocity(0, 0);
        Filter filter = fixture.getFilterData();
        fixture.setFilterData(new Filter());
//        filter.maskBits |= PLAYER_BITS;
//        filter.maskBits |= PORTAL_BITS;
        grabbedEntity = null;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Level getLevel() {
        return level;
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

    public boolean isStanding() {
        return standing;
    }

    public void setStanding(boolean standing) {
        this.standing = standing;
    }

    public boolean canGrabEntity() {
        return grabbedEntity == null;
    }

}
