package com.portal2d.game.model.weapons;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.EntityType;

import static com.portal2d.game.model.ModelConstants.GRAVITY_GUN_RADIUS;

/**
 * Query callback for the GravityGun to find out which bodies are around it's owner and can be grabbed.
 * @see World#QueryAABB(QueryCallback, float, float, float, float)
 * @see QueryCallback
 */
public class GravityGunQuery implements QueryCallback {

    private GravityGun gravityGun;
    private World world;

    // AABB
    private float lowerX;
    private float lowerY;
    private float upperX;
    private float upperY;

    public GravityGunQuery(GravityGun gravityGun) {
        this.gravityGun = gravityGun;
        world = gravityGun.level.getWorld();
    }

    @Override
    public boolean reportFixture(Fixture fixture) {

        Entity entity = (Entity) fixture.getBody().getUserData();

        //Only grab dynamic entities
        if(entity.getType().equals(EntityType.BOX) && gravityGun.canGrabEntity()) {
            gravityGun.grabEntity(entity);
            return false; //terminates the query
        }

        return true;
    }

    public void updateAABB() {
        Entity owner = gravityGun.getOwner();
        Body ownerBody = owner.getBody();

        lowerX = ownerBody.getPosition().x - owner.getType().getWidth() / 2 - GRAVITY_GUN_RADIUS;
        lowerY = ownerBody.getPosition().y - owner.getType().getHeight() / 2 - GRAVITY_GUN_RADIUS;
        upperX = ownerBody.getPosition().x + owner.getType().getWidth() / 2 + GRAVITY_GUN_RADIUS;
        upperY = ownerBody.getPosition().y + owner.getType().getHeight() / 2 + GRAVITY_GUN_RADIUS;
    }

    public void queryAABB() {
        updateAABB();
        world.QueryAABB(this, lowerX, lowerY, upperX, upperY);
    }

    //TESTEO
    public float getLowerX() {
        return lowerX;
    }

    public float getLowerY() {
        return lowerY;
    }

    public float getUpperX() {
        return upperX;
    }

    public float getUpperY() {
        return upperY;
    }
}
