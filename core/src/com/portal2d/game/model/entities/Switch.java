package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 * Entity that sends an order to it's {@link #switchable}.
 * @see Button
 */
public abstract class Switch extends StaticEntity {

    protected Switchable switchable;

    public Switch(Level level, Body body, EntityType type, Switchable switchable) {
        super(level, body, type);
        this.switchable = switchable;
    }

}
