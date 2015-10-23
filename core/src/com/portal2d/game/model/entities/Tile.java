package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class Tile extends StaticEntity {

    public enum Type {
        PORTAL_ABLE,
        NON_PORTAL_ABLE,
    }

    Type type;

    public Tile(World world, Body body, Type type) {
        super(world, body);
        this.type = type;
    }

    @Override
    public void interact(Box box) {


    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void interact(Exit exit) {

    }

    @Override
    public void interact(Button button) {

    }

    @Override
    public void interact(Tile tile) {

    }
}
