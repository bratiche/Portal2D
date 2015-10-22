package com.portal2d.game.model.weapons;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.portals.BluePortal;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.OrangePortal;

/**
 *
 */
public class PortalGun implements Weapon {

    private Entity owner;
    private BluePortal bluePortal;
    private OrangePortal orangePortal;

    public PortalGun(Entity owner) {
        this.owner = owner;
    }

    private void shootBluePortal() {

    }

    private void shootOrangePortal() {

    }

    public void actionLeftClick(Vector2 position) {
        shootBluePortal();
    }

    public void actionRightClick(Vector2 position) {
        shootOrangePortal();
    }

    public BluePortal getBluePortal() {
        return bluePortal;
    }

    public OrangePortal getOrangePortal() {
        return orangePortal;
    }
}
