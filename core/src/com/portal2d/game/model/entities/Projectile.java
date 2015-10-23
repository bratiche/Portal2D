package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * Base entity for all projectiles in the game.
 */
public class Projectile extends DynamicEntity {

    public Projectile(World world, Body body) {
        super(world, body);
        body.setType(BodyDef.BodyType.KinematicBody);
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
