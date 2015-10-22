package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.model.weapons.Weapon;

/**
 *
 */
public class Player extends DynamicEntity {

    private Weapon weapon;

    public Player(World world, Body body) {
        super(world, body);

        weapon = new PortalGun(this);
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
