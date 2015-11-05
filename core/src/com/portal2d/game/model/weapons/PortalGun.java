package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

import java.util.NoSuchElementException;

/**
 *
 */
public class PortalGun implements Weapon {

    private Entity owner;
    private Portal bluePortal;
    private Portal orangePortal;

    private PortalGunRayCast raycast;

    private World world;
    private Level level;

    public PortalGun(Level level, Entity owner) {
        this.level = level;
        this.owner = owner;
        this.world = level.getWorld();
        this.raycast = new PortalGunRayCast(this, level);
    }

    @Override
    public void actionLeftClick(Vector2 position) {
        raycast.setPortalColor(PortalColor.BLUE);
        shoot(position);
    }

    @Override
    public void actionRightClick(Vector2 position) {
        raycast.setPortalColor(PortalColor.ORANGE);
        shoot(position);
    }

    /**
     * Ray-cast the world using the {@link PortalGunRayCast} callback.
     * @see World#rayCast(RayCastCallback, Vector2, Vector2)
     */
    private void shoot(Vector2 position) {
        raycast.restartRay();
        Vector2 beginPoint = new Vector2(owner.getBody().getPosition());

        Vector2 step = new Vector2(position);
        step.sub(owner.getBody().getPosition());
        step.nor();
        step.scl(RAY_CAST_STEP);

        Vector2 endPoint = new Vector2(owner.getBody().getPosition());
        endPoint.add(step);

        while(!raycast.hit()) {
            world.rayCast(raycast, beginPoint, endPoint);
            endPoint.add(step);
        }
    }

    public void setPortal(Portal portal) {
        switch(portal.getColor()){
            case BLUE:
                bluePortal = portal;
                break;
            case ORANGE:
                orangePortal = portal;
                break;
        }
    }

    public Portal getPortal(PortalColor color) {
        switch(color) {
            case BLUE:
                return bluePortal;
            case ORANGE:
                return orangePortal;
            default:
                throw new NoSuchElementException(); //TODO: Add own exception
        }
    }

    public void linkPortals() {
        if(orangePortal != null && bluePortal != null){
            bluePortal.setOppositePortal(orangePortal);
            orangePortal.setOppositePortal(bluePortal);
        }
    }
}
