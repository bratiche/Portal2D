package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class Tile extends StaticEntity {

    public enum Type {
        PORTAL_ABLE,
        NON_PORTAL_ABLE,
    }

    Type type;

    public Tile(Level level, Body body, Type type) {
        super(level, body);
        this.type = type;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    @Override
    public void beginInteraction(Box box) {


    }

    @Override
    public void beginInteraction(Player player) {

    }

    @Override
    public void beginInteraction(Exit exit) {

    }

    @Override
    public void beginInteraction(Button button) {

    }

}
