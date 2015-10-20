package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
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

    public Tile(Body body, Type type) {
        super(body);
        this.type = type;
    }
}
