package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.corba.se.impl.orbutil.closure.Constant;

/**
 * Created by Belén on 21/10/2015.
 */
public class Projectile extends DynamicEntity {

    public Projectile(World world, Body body) {
        super(world, body);
        body.setType(BodyDef.BodyType.KinematicBody);
    }
}
