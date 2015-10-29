package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.entities.portals.BluePortal;
import com.portal2d.game.model.entities.portals.OrangePortal;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.PROJECTILE_RADIUS;
import static com.portal2d.game.model.ModelConstants.PROJECTILE_SPEED;

/**
 *
 */
public class PortalGun implements Weapon {

    private Entity owner;
    private BluePortal bluePortal;
    private OrangePortal orangePortal;

    private World world;
    private Level level;

    public PortalGun(Level level, Entity owner) {
        this.level = level;
        this.owner = owner;
        this.world = level.getWorld();
    }

    //TODO: replace with ray cast in PortalProjectile
    private void shootBluePortal(Vector2 position) {

        if(bluePortal == null) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(position);
            Body body = world.createBody(bodyDef);

            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(0.1f);

            Fixture fixture = body.createFixture(circleShape, 1);
            fixture.setSensor(true);
            bluePortal = new BluePortal(level, body, this);

            circleShape.dispose();
        }
        else
            bluePortal.getBody().setTransform(position, 0);
    }

    private void shootOrangePortal(Vector2 position) {

        if(orangePortal == null) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(position);
            Body body = world.createBody(bodyDef);

            CircleShape circleShape = new CircleShape();
            circleShape.setRadius(0.1f);

            Fixture fixture = body.createFixture(circleShape, 1);
            fixture.setSensor(true);
            orangePortal = new OrangePortal(level, body, this);

            circleShape.dispose();
        }
        else {
            orangePortal.getBody().setTransform(position, 0);
        }

    }

    /**
     * Placeholder until a proper method is implemented
     */
    private void shootProjectile(Vector2 position) {
        Vector2 own_pos = owner.getBody().getPosition();

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(own_pos);

        //calculate constant velocity
        Vector2 velocity = new Vector2(position.sub(own_pos));
        velocity.nor();
        velocity.scl(PROJECTILE_SPEED);

        Body body = world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(PROJECTILE_RADIUS);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setSensor(true);

        //not affected by gravity
        body.setGravityScale(0);

        level.add(new Projectile(level, body, velocity));
    }

    public void actionLeftClick(Vector2 position) {
        shootBluePortal(position);
    }

    public void actionRightClick(Vector2 position) {
        shootOrangePortal(position);
    }

    public BluePortal getBluePortal() {
        return bluePortal;
    }

    public OrangePortal getOrangePortal() {
        return orangePortal;
    }

    public void update(){
        if(bluePortal != null && orangePortal != null) {
            bluePortal.update();
            orangePortal.update();
        }
    }
}
