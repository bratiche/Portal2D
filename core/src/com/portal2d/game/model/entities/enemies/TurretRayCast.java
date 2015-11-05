package com.portal2d.game.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class TurretRayCast implements RayCastCallback {

    private Turret turret;
    private Level level;
    private World world;
    private boolean hit;

    public TurretRayCast(Turret turret, Level level) {
        this.turret = turret;
        this.level = level;
        this.world = level.getWorld();
    }

    @Override
    public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {

        Entity entity = (Entity)fixture.getBody().getUserData();
        EntityType type = entity.getType();

        if(type == EntityType.PROJECTILE) {
            return -1;
        }

        if(type == EntityType.PLAYER){
            turret.shoot(fixture.getBody().getPosition().scl(fraction));
        }

        hit = true;
        return 0;
    }

    public boolean hit() {
        return hit;
    }

    public void restartRay() {
        hit = false;
    }
}
