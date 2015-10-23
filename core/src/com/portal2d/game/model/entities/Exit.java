package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.level.LevelName;
import com.portal2d.game.model.interactions.EntityType;

/**
 * An entity the player has to get to to change the current level.
 */
public class Exit extends StaticEntity {

    private LevelName destinyLevel;

    /**
     * @param destinyLevel Es el nivel al que lleva la exit
     */
    public Exit(World world, Body body, LevelName destinyLevel) {
        super(world, body);
        this.destinyLevel = destinyLevel;
        type = EntityType.EXIT;
        body.setUserData(this);
    }

    public LevelName getDestinyLevel() {
        return destinyLevel;
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
