package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.interactions.RayCast;
import com.portal2d.game.model.level.Level;

import java.util.NoSuchElementException;

import static com.portal2d.game.model.ModelConstants.RAY_CAST_STEP_LENGTH;

/**
 * Weapon that manages the creation and destruction of {@link Portal}s.
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
     * Shoots a blue portal if the gun has no entity grabbed,
     * otherwise throws the entity at the specified position.
     */
    @Override
    public void actionLeftClick(Vector2 position) {
        if(hasEntityGrabbed()) {
            super.actionLeftClick(position);
        }
        else {
            raycast.setPortalColor(PortalColor.BLUE);
            shoot(position);
        }
    }

    /** Shoots an orange portal if the gun has no entity grabbed. */
    @Override
    public void actionRightClick(Vector2 position) {
        if(!hasEntityGrabbed()) {
            raycast.setPortalColor(PortalColor.ORANGE);
            shoot(position);
        }
    }

    /**
     * Ray-cast the world using the {@link PortalGunRayCast} callback.
     * @see RayCast#process()
     */
    private void shoot(Vector2 position) {
        raycast.setRay(owner.getBody().getPosition(), position, RAY_CAST_STEP_LENGTH);
        raycast.process();
    }

    /** @see PortalGunRayCast#createPortal(Vector2, Vector2) */
    protected void setPortal(Portal portal) {
        switch(portal.getColor()){
            case BLUE:
                bluePortal = portal;
                break;
            case ORANGE:
                orangePortal = portal;
                break;
        }
    }

    /**
     * Returns the portal of the specified color.
     * It may return {@code null} if the portal is not created yet.
     * @see PortalGunRayCast#createPortal(Vector2, Vector2)
     */
    protected Portal getPortal(PortalColor color) {
        switch(color) {
            case BLUE:
                return bluePortal;
            case ORANGE:
                return orangePortal;
            default:
                throw new NoSuchElementException(color + " ïs not a valid PortalColor");
        }
    }

    protected void linkPortals() {
        if(orangePortal != null && bluePortal != null){
            bluePortal.setOppositePortal(orangePortal);
            orangePortal.setOppositePortal(bluePortal);
        }
    }

    /** Destroys both portals. */
    public void destroyPortals() {

        if(bluePortal != null) {
            level.addToRemove(bluePortal);
            bluePortal = null;
        }

        if(orangePortal != null) {
            level.addToRemove(orangePortal);
            orangePortal = null;
        }
    }


    /** Returns whether the portal of the specified color is created */
    public boolean isPortalCreated(PortalColor color) {
        switch (color) {
            case BLUE:
                return bluePortal != null;
            case ORANGE:
                return orangePortal != null;
            default:
                throw new NoSuchElementException(color + " ïs not a valid PortalColor");
        }
    }

    public boolean arePortalsLinked() {
        return orangePortal != null && bluePortal != null;
    }

    //TESTEO
    public PortalGunRayCast getRayCast() {
        return raycast;
    }
}
