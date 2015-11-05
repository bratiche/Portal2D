package com.portal2d.game.model.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.StaticEntity;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 *
 */
public class Turret extends StaticEntity {

    private TurretRayCast raycast;
    private Vector2 pointToShoot;
    private boolean targetLockedOn;
    private Entity target;

    // Rate of fire in seconds
    private float rateOfFire = 0.5f;

    public Turret(Level level, Body body) {
        super(level, body);
        type = EntityType.TURRET;
        pointToShoot = new Vector2();
        raycast = new TurretRayCast(this, level);

        //Add vision fixture
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(TURRET_VISION_RADIUS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = circleShape;

        this.body.createFixture(fixtureDef);
        circleShape.dispose();
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
    public void beginInteraction(Player player) {
        target = player;
        targetLockedOn = true;
    }

    @Override
    public void endInteraction(Player player) {
        targetLockedOn = false;
    }

    private float time = 0;
    public void update() {
        time += Gdx.graphics.getDeltaTime();
        if(targetLockedOn && time >= rateOfFire) {
            pointToShoot.set(target.getBody().getPosition());
            rayCast();
            time = 0;
        }
    }

    public void rayCast() {
        raycast.restartRay();
        Vector2 beginPoint = new Vector2(body.getPosition());

        Vector2 step = new Vector2(pointToShoot);
        step.sub(beginPoint);
        step.nor();
        step.scl(RAY_CAST_STEP);

        Vector2 endPoint = new Vector2(beginPoint);
        endPoint.add(step);

        while(!raycast.hit()) {
            world.rayCast(raycast, beginPoint, endPoint);
            endPoint.add(step);
        }
    }

    public void shoot(Vector2 position) {

        Vector2 direction = new Vector2(position);
        direction.sub(this.body.getPosition());
        direction.nor();

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.body.getPosition());

        CircleShape shape = new CircleShape();
        shape.setRadius(PROJECTILE_RADIUS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        level.add(new Missile(level, body, new Vector2(direction.x * PROJECTILE_SPEED, direction.y * PROJECTILE_SPEED)));
    }

    /**
     * Overridden so the turret doesn't destroy the projectile.
     */
    @Override
    public void beginInteraction(Missile missile) {

    }
}
