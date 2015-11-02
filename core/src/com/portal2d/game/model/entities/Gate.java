package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Gate extends StaticEntity implements Linkable {

    private boolean open;
    private Set<Button> buttons;

    public Gate(Level level, Body body, Button...locks) {
        super(level, body);
        type = EntityType.GATE;

        //TODO implement this
        buttons = new HashSet<Button>();

        Collections.addAll(buttons, locks);
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
    public void beginInteraction(Missile missile) {
        if(!open)
            level.addToRemove(missile);
    }

    @Override
    public void update() {
        for(Button button : buttons) {
            open &= button.isPressed();
        }
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
