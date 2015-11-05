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
public class PortalGun extends GravityGun {

    private Portal bluePortal;
    private Portal orangePortal;

    private PortalGunRayCast raycast;

    public PortalGun(Level level, Entity owner) {
        super(level, owner);
        this.raycast = new PortalGunRayCast(this, level);
    }

    /**
     * If this gun has an entity grabbed, throw it away.
     * Else shoot a blue portal.
     */
    @Override
    public void actionLeftClick(Vector2 position) {
        if(grabbedEntity != null) {
            super.actionLeftClick(position);
        }
        else {
            raycast.setPortalColor(PortalColor.BLUE);
            shoot(position);
        }
    }

    /**
     * Only shoot an orange portal if the gun has no entity grabbed.
     */
    @Override
    public void actionRightClick(Vector2 position) {
        if(grabbedEntity == null) {
            raycast.setPortalColor(PortalColor.ORANGE);
            shoot(position);
        }
    }

    /**
     * Ray-cast the world using the {@link PortalGunRayCast} callback.
     * @see World#rayCast(RayCastCallback, Vector2, Vector2)
     */
    private void shoot(Vector2 position) {
        raycast.setRay(owner.getBody().getPosition(), position, RAY_CAST_STEP_LENGTH);
        raycast.process();
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

    //TESTEO
    public PortalGunRayCast getRayCast() {
        return raycast;
    }
}
