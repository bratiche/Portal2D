package com.portal2d.game.model.entities.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.StaticEntity;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 * Entity that shoots {@link Bullet}s at the {@link Player} if it is in range.
 */
public class Turret extends StaticEntity {

    private TurretRayCast raycast;
    private Entity target;

    public Turret(Level level, Vector2 position) {
        super(level, position, EntityType.TURRET);

        createVisionFixture();

        raycast = new TurretRayCast(world, this);
    }

    private void createVisionFixture() {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(TURRET_VISION_RADIUS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = circleShape;

        body.createFixture(fixtureDef);
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
    }

    @Override
    public void endInteraction(Player player) {
        target = null;
    }

    private float time = 0;

    @Override
    public void update() {
        time += Gdx.graphics.getDeltaTime();
        if(targetLockedOn() && time >= TURRET_RATE_OF_FIRE) {
            raycast.setRay(this.getPosition(), target.getPosition(), RAY_CAST_STEP_LENGTH);
            raycast.process();
            time = 0;
        }
    }

    public boolean targetLockedOn() {
        return target != null;
    }

    /** Shoots a {@link Bullet} at the specified position */
    public void shoot(Vector2 position) {

        Vector2 direction = new Vector2(position);
        direction.sub(this.getPosition());
        direction.nor();
        this.setAngle(direction.angleRad());

        this.setTransform(body.getPosition(), direction.angleRad());

        Vector2 velocity = new Vector2(direction.x * BULLET_SPEED, direction.y * BULLET_SPEED);

        Bullet bullet = new Bullet(level, this.getPosition(), velocity);
        bullet.setAngle(direction.angleRad());
        level.add(bullet);
    }

    /** Overridden so the turret doesn't destroy it's own bullets. */
    @Override
    public void beginInteraction(Bullet bullet) {

    }

    public Vector2 getTargetPosition() {
        return target.getPosition();
    }

}
