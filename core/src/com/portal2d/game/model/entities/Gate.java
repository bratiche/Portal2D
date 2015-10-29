package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class Gate extends StaticEntity implements Linkable {

    private boolean open;

    public Gate(Level level, Body body) {
        super(level, body);
        type = EntityType.GATE;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    /**
     * The projectile is destroyed only if this gate is closed.
     */
    @Override
    public void beginInteraction(Projectile projectile) {
        if(!open)
            level.addToRemove(projectile);
    }


    @Override
    public void link() {
        open = true;
        body.getFixtureList().get(0).setSensor(true);
    }

    @Override
    public void unlink() {
        open = false;
        body.getFixtureList().get(0).setSensor(false);
    }

    public boolean isOpen() {
        return open;
    }

}
