package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.interactions.EntityType;

/**
 * An entity that doesn't do anything. Basically dead weight to solve puzzles.
 */
public class Box extends DynamicEntity {

    public Box(Level level, Body body) {
        super(level, body);
        type = EntityType.BOX;
        body.setUserData(this);
    }

    /**
     * If we don't override these two methods for every entity we get StackOverflowError.
     */
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
