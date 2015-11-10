package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.level.Level;

/**
 * An obstacle that can be overcome by pressing all it's {@link Switch}es.
 */
public class Gate extends StaticEntity implements Switchable {

    private boolean open;

    /** The number of switches this gate has */
    private int switches;

    public Gate(Level level, Body body, int switches) {
        super(level, body, EntityType.GATE);
        this.switches = switches;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    /** The bullet is destroyed only if this gate is closed. */
    @Override
    public void beginInteraction(Bullet bullet) {
        if(!open)
            level.addToRemove(bullet);
    }

    @Override
    public void update() {
        if(switches == 0 && !open) {
            open = true;
            this.setSensor(true);
        }
        else if (switches > 0 && open) {
            open = false;
            this.setSensor(false);
        }
    }

    @Override
    public void switchOn() {
        switches--;
    }

    @Override
    public void switchOff() {
        switches++;
    }

    public boolean isOpen() {
        return open;
    }

}
