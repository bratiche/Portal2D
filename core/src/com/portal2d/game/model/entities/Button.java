package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.interactions.EntityType;

/**
 * An entity that sends an order to it's linked Entity whenever it's pressed.
 */
public class Button extends StaticEntity {

    private boolean pressed;
    private int interactions;
    private Linkable linkedEntity;

    public Button(Level level, Body body, Linkable linkedEntity) {
        super(level, body);
        this.linkedEntity = linkedEntity;
        type = EntityType.BUTTON;
    }

    public void update() {
        if(interactions > 0 && !pressed) {
            pressed = true;
            linkedEntity.link();
        }
        else if(interactions == 0 && pressed) {
            pressed = false;
            linkedEntity.unlink();
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
