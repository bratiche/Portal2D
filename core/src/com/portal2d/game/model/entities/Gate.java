package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class Gate extends StaticEntity implements Linkable {

    private boolean opened;

    public Gate(Level level, Body body) {
        super(level, body);
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
    public void link() {
        opened = true;
        body.getFixtureList().get(0).setSensor(true);
    }

    @Override
    public void unlink() {
        opened = false;
        body.getFixtureList().get(0).setSensor(false);
    }
}
