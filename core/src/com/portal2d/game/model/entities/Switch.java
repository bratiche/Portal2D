package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.level.Level;

/**
 * Entity that sends an order to it's {@link #switchable}.
 * @see Button
 */
public abstract class Switch extends StaticEntity {

    protected Switchable switchable;

    public Switch(Level level, Vector2 position, EntityType type, Switchable switchable) {
        super(level, position, type);
        this.switchable = switchable;
    }

}
