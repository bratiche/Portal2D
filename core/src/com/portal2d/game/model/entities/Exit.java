package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class Exit extends StaticEntity {

    private int destinyLevel;

    /**
     * @param body
     * @param destinyLevel Es el nivel al que lleva la exit
     */
    public Exit(World world, Body body, int destinyLevel) {
        super(world, body);
        this.destinyLevel = destinyLevel;
    }

    public int getDestinyLevel() {
        return destinyLevel;
    }

}
