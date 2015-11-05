package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.Spring;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class GravityGun implements Weapon {

    protected Entity owner;
    protected Level level;
    protected World world;

    protected Entity grabbedEntity;
    protected GravityGunQuery query;

    protected Spring spring;

    public GravityGun(Level level, Entity owner) {
        this.owner = owner;
        this.level = level;
        this.world = level.getWorld();

        spring = new Spring(world);
        query = new GravityGunQuery(this);
    }

    public final float SPEED = 10f;

    @Override
    public void actionLeftClick(Vector2 position) {
        // Set the grabbed entity new position / velocity
        Vector2 direction = new Vector2(position);
        direction.sub(owner.getBody().getPosition());
        direction.nor();

        // Shoot the grabbed entity
        grabbedEntity.getBody().setLinearVelocity(direction.scl(SPEED));
        // Drop the grabbed entity
        dropEntity();
    }

    @Override
    public void actionRightClick(Vector2 position) {
        //do nothing for now...
    }

    @Override
    public void update(Vector2 position) {
        spring.update(position);
    }

    public void grabEntity(Entity entity) {
        System.out.println("ENTITY GRABBED");
        //create joint
        spring.setBodies(owner.getBody(), entity.getBody());

        grabbedEntity = entity;
    }

    public void dropEntity() {
        System.out.println("ENTITY DROPPED");
        //destroy joint
        spring.destroy();

        grabbedEntity.getBody().setGravityScale(1); // gravity back to normal
        grabbedEntity = null;
    }

    public boolean canGrabEntity() {
        return grabbedEntity == null;
    }

    protected Entity getOwner() {
        return owner;
    }

    public void queryAABB() {
        query.queryAABB();
    }

    //TESTEO
    public GravityGunQuery getQuery() {
        return query;
    }

}
