package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalProjectile;
import com.portal2d.game.model.interactions.PortalColor;
import com.portal2d.game.model.level.Level;

import java.util.NoSuchElementException;

import static com.portal2d.game.model.ModelConstants.PROJECTILE_SPEED;

/**
 *
 */
public class PortalGun implements Weapon {

    private Entity owner;
    private Portal bluePortal;
    private Portal orangePortal;

    private World world;
    private Level level;

    public PortalGun(Level level, Entity owner) {
        this.level = level;
        this.owner = owner;
        this.world = level.getWorld();
    }
    /**
     * Placeholder until a proper method is implemented
     */
    private void shootProjectile(Vector2 position, PortalColor color) {
        Vector2 own_pos = owner.getBody().getPosition();

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(own_pos);

        //calculate constant velocity
        Vector2 velocity = new Vector2(position.sub(own_pos));
        velocity.nor();
        velocity.scl(PROJECTILE_SPEED);

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.1f, 0.1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);
        //fixture.setSensor(true); doesn't detect normals if it's is sensor

        //not affected by gravity
        //body.setGravityScale(0);

        level.add(new PortalProjectile(level, body, color, this, velocity));
    }

    public void actionLeftClick(Vector2 position) {
        shootProjectile(position, PortalColor.BLUE);
    }

//        public void actionLeftClick(Vector2 position) {
//        shootProjectile(position);
//    }

    public void actionRightClick(Vector2 position) {
        shootProjectile(position, PortalColor.ORANGE);
    }

    public void setPortal(Portal portal){
        switch(portal.getColor()){
            case BLUE:
                bluePortal = portal;
                break;
            case ORANGE:
                orangePortal = portal;
                break;
        }
    }

    public Portal getPortal(PortalColor color){
        switch(color){
            case BLUE:
                return bluePortal;
            case ORANGE:
                return orangePortal;
            default:
                throw new NoSuchElementException(); //TODO: Add own exception
        }
    }

    public void linkPortals(){
        if(orangePortal != null && bluePortal != null){
            bluePortal.setOppositePortal(orangePortal);
            orangePortal.setOppositePortal(bluePortal);
        }
    }
}
