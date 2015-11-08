package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.view.scenes.PlayScene;

/**
 * Visual representation of an {@link Entity}, it takes a generic parameter in the constructor
 * indicating the model to draw.
 */
public abstract class EntityView<T extends Entity> {

    protected T model;

    protected float width;
    protected float height;

    public EntityView(T model) {
        this.model = model;
    }

    /**
     * Updates this EntityView, called by {@link PlayScene} each frame.
     */
    public abstract void render(SpriteBatch batch, float deltaTime);

    public Vector2 getPosition() {
        return model.getPosition();
    }

}
