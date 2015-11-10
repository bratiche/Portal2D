package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;

/**
 * Sends an order to it's {@link #switchable} whenever it's pressed or released.
 */
public class Button extends Switch {

    private boolean pressed;
    private int interactions;

    public Button(Level level, Body body, Switchable linkedEntity) {
        super(level, body, EntityType.BUTTON, linkedEntity);
    }

    @Override
    public void update() {
        if(interactions > 0 && !pressed) {
            pressed = true;
            switchable.switchOn();
        }
        else if(interactions == 0 && pressed) {
            pressed = false;
            switchable.switchOff();
        }
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        interactions++;
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
        interactions--;
    }

    public boolean isPressed() {
        return pressed;
    }

}
