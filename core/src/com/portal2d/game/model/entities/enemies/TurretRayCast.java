package com.portal2d.game.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.interactions.RayCast;

/**
 * {@link RayCast} used by a Turret to to shoot at it's target.
 */
public class TurretRayCast extends RayCast {

    private Turret turret;

    public TurretRayCast(World world, Turret turret) {
        super(world);
        this.turret = turret;
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {

        Entity entity = (Entity)fixture.getBody().getUserData();
        EntityType type = entity.getType();

        if(type == EntityType.BULLET) {
            return -1;
        }

        if(type == EntityType.PLAYER){
            turret.shoot(fixture.getBody().getPosition());
        }

        hit = true;
        return 0;
    }

}
