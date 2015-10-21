package com.portal2d.game.controller.events.events;

import com.portal2d.game.controller.events.listeners.CollisionListener;
import com.portal2d.game.model.entities.Button;
import com.portal2d.game.model.entities.DynamicEntity;

/**
 * Collision between a button and any entity
 */
public class ButtonCollision implements GameEvent<CollisionListener> {

    private Button button;
    private DynamicEntity entity;

    public ButtonCollision(Button button, DynamicEntity entity) {
        this.button = button;
        this.entity = entity;
    }

    @Override
    public void notify(CollisionListener listener) {
        listener.entityCollidedWithButton(entity, button);
    }
}
