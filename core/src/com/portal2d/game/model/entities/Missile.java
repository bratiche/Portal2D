package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class Missile extends Projectile {

    public Missile(Level level, Body body, Vector2 velocity) {
        super(level, body, velocity);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

// TODO: implement for turrets
//    private void shootProjectile(Vector2 position, PortalColor color) {
//        Vector2 own_pos = owner.getBody().getPosition();
//
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.position.set(own_pos);
//
//        //calculate constant velocity
//        Vector2 velocity = new Vector2(position.sub(own_pos));
//        velocity.nor();
//        velocity.scl(PROJECTILE_SPEED);
//
//        Body body = world.createBody(bodyDef);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(0.1f, 0.1f);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//
//        Fixture fixture = body.createFixture(fixtureDef);
//        //fixture.setSensor(true); doesn't detect normals if it's a sensor
//
//        //not affected by gravity
//        //body.setGravityScale(0);
//
//        level.add(new PortalProjectile(level, body, color, this, velocity));
//    }

}
