package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.interactions.EntityType;

/**
 * An entity that doesn't do anything. Basically dead weight to solve puzzles.
 */
public class Box extends DynamicEntity {

    public Box(World world, Body body) {
        super(world, body);
        type = EntityType.BOX;
        body.setUserData(this);
    }

    @Override
    public void interact(Box box) {

    }

    @Override
    public void interact(Player player) {
        System.out.println("Caja-Player");

    }

    @Override
    public void interact(Exit exit) {

    }

    @Override
    public void interact(Button button) {
        System.out.println("Caja-button");

    }

    @Override
    public void interact(Tile tile) {

    }
}
