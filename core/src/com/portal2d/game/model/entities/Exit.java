package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 */
public class Exit extends StaticEntity {

    private int nextLevel;

    public Exit(Body body, int nextLevel) {
        super(body);
    }

    public int getNextLevel() {
        return nextLevel;
    }

}
