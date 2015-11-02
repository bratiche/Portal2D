package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.level.Level;

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
        rayCast(position);
    }

    @Override
    public void actionRightClick(Vector2 position) {
        raycast.setPortalColor(PortalColor.ORANGE);
        rayCast(position);
    }

    /**
     * Ray-cast the world using the {@link PortalGunRayCast} callback.
     * @see World#rayCast(RayCastCallback, Vector2, Vector2)
     */
    private void rayCast(Vector2 position) {
        Vector2 beginPoint = new Vector2(owner.getBody().getPosition());
        //beginPoint.add(0, 0.4f);

        Vector2 distance = new Vector2(position);
        distance.sub(owner.getBody().getPosition());
        distance.nor();
        //System.out.println(distance);
        distance.scl(10f);
        Vector2 endPoint = new Vector2(owner.getBody().getPosition());
        endPoint.add(distance);

        world.rayCast(raycast, beginPoint, endPoint);
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
