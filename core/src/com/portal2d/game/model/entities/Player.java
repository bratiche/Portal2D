package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.model.weapons.Weapon;

/**
 * The main entity in the game, the user controls this entity.
 */
public class Player extends DynamicEntity {

    private Weapon weapon;

    public Player(World world, Body body) {
        super(world, body);
        type = EntityType.PLAYER;
        body.setUserData(this);
        weapon = new PortalGun(this);
    }

    @Override
    public void interact(Box box) {
        System.out.println("Player-Caja");
    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void interact(Exit exit) {

    }

    @Override
    public void interact(Button button) {
        System.out.println("Player-Boton");
    }

    @Override
    public void interact(Tile tile) {

    }

    public Weapon getWeapon() {
        return weapon;
    }

}
