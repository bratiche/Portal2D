package com.portal2d.game.model.entities;

/**
 * Created by Belén on 21/10/2015.
 */
public class PortalGun implements Weapon {

    private Entity owner;
    private BluePortal bluePortal;
    private OrangePortal orangePortal;

    public PortalGun(Entity owner){
        this.owner = owner;
    }


    private void shootBluePortal(){

    }

    private void shootOrangePortal() {

    }

    public void actionLeftClick() {
        shootBluePortal();
    }


    public void actionRightClick() {
        shootOrangePortal();
    }

    public BluePortal getBluePortal() {
        return this.bluePortal;
    }

    public OrangePortal getOrangePortal() {
        return this.orangePortal;
    }
}
