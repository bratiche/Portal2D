package com.portal2d.game.model.entities.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 * Projectiles fired by {@link Turret}s.
 */
public class Bullet extends Projectile {

    public Bullet(Level level, Vector2 position, Vector2 velocity) {
        super(level, position, velocity, EntityType.BULLET);

        CircleShape shape = new CircleShape();
        shape.setRadius(BULLET_RADIUS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);
        body.setGravityScale(0);

        shape.dispose();
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    /** Overridden so bullets don't destroy each other on collision. */
    @Override
    public void beginInteraction(Bullet projectile) {

    }

    @Override
    public void beginInteraction(Player player) {
        //player.die();
    }

}
