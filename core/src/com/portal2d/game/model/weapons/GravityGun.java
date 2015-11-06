package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.Spring;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 * Weapon that allows the owner to manipulate an object by grabbing it.
 */
public class GravityGun implements Weapon {

    protected Entity owner;
    protected Level level;
    protected World world;
    protected GravityGunQuery query;

    protected Spring spring;
    protected Entity grabbedEntity;

    public GravityGun(Level level, Entity owner) {
        this.owner = owner;
        this.level = level;
        this.world = level.getWorld();

        spring = new Spring(world, GRAVITY_GUN_RADIUS);
        query = new GravityGunQuery(this);
    }

    public final float SPEED = 10f;

    @Override
    public void actionLeftClick(Vector2 position) {
        // Shoot the grabbed entity
        Vector2 direction = new Vector2(position);
        direction.sub(owner.getBody().getPosition());
        direction.nor();

        grabbedEntity.getBody().setLinearVelocity(direction.scl(SPEED));

        dropEntity();
    }

    @Override
    public void actionRightClick(Vector2 position) {
        //do nothing for now...
    }

    @Override
    public void update(Vector2 position) {
        if(grabbedEntity != null) {
            spring.update(position);
        }
    }

    public void grabEntity(Entity entity) {
        System.out.println("ENTITY GRABBED");
        spring.setBodies(owner.getBody(), entity.getBody());

        grabbedEntity = entity;
        grabbedEntity.getBody().setGravityScale(0); // this body is no longer affected by gravity
        grabbedEntity.getBody().getFixtureList().get(0).setSensor(true);
    }

    public void dropEntity() {
        System.out.println("ENTITY DROPPED");
        spring.destroy();

        grabbedEntity.getBody().setGravityScale(1); // gravity back to normal
        grabbedEntity.getBody().getFixtureList().get(0).setSensor(false);
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
