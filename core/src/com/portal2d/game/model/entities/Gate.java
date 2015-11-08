package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

/**
 *
 */
public class Gate extends StaticEntity implements Linkable {

    private boolean open;
    private int locks;

    public Gate(Level level, Body body, int locks) {
        super(level, body, EntityType.GATE);
        this.locks = locks;
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
        if(locks == 0 && !open) {
            open = true;
            this.setSensor(true);
        }
        else if (locks > 0 && open) {
            open = false;
            this.setSensor(false);
        }
    }

    @Override
    public void buttonPressed() {
        locks--;
    }

    @Override
    public void buttonReleased() {
        locks++;
    }

    public boolean isOpen() {
        return open;
    }

}
