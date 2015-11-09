package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.Spring;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.GRAVITY_GUN_RANGE;

/**
 * Weapon that allows the owner to manipulate an object by grabbing it.
 */
public class GravityGun implements Weapon {

    protected Entity owner;
    protected Level level;
    protected GravityGunQuery query;

    protected Spring spring;
    protected Entity grabbedEntity;

    public GravityGun(Level level, Entity owner) {
        this.owner = owner;
        this.level = level;

        query = new GravityGunQuery(level.getWorld(), this);
        spring = new Spring(level.getWorld(), GRAVITY_GUN_RANGE);
    }

    // Speed for throwing away objects
    public final float SPEED = 10f;

    /** Shoots the grabbed entity. */
    @Override
    public void actionLeftClick(Vector2 position) {
        Vector2 direction = new Vector2(position);
        direction.sub(owner.getPosition());
        direction.nor();

        grabbedEntity.setLinearVelocity(direction.scl(SPEED));

        dropEntity();
    }

    @Override
    public void actionRightClick(Vector2 position) {
        //do nothing for now...
    }

    @Override
    public void update(Vector2 position) {
        if(hasEntityGrabbed()) {
            spring.update(position);
        }
    }

    /**
     * Creates a joint between the {@link #owner} and the specified entity
     * @param entity the entity to grab
     */
     public void grabEntity(Entity entity) {
        spring.setBodies(owner.getBody(), entity.getBody());

        grabbedEntity = entity;
        grabbedEntity.getBody().setGravityScale(0); // this body is no longer affected by gravity
        grabbedEntity.setSensor(true);
    }

    /** Destroys the joint between the owner and the grabbed entity. */
    public void dropEntity() {
        spring.destroy();

        grabbedEntity.getBody().setGravityScale(1); // gravity back to normal
        grabbedEntity.setSensor(false);
        grabbedEntity = null;
    }

    public boolean canGrabEntity() {
        return grabbedEntity == null;
    }

    public boolean hasEntityGrabbed() {
        return grabbedEntity != null;
    }

    /** @see GravityGunQuery#queryAABB() */
    public void queryAABB() {
        query.queryAABB();
    }

    protected Entity getOwner() {
        return owner;
    }

    //TESTEO
    public GravityGunQuery getQuery() {
        return query;
    }

}
