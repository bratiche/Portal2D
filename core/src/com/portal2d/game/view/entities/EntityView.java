package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.view.scenes.PlayScene;

import java.util.HashMap;
import java.util.Map;

/**
 * Visual representation of an {@link Entity}, it takes a generic parameter in the constructor
 * indicating the model to draw.
 */
public abstract class EntityView<T extends Entity> {

    protected T model;
    protected Body body;

    protected float width;
    protected float height;

    protected Map<Action, Animation> animations;

    public EntityView(T model) {
        this.model = model;
        body = model.getBody();
        animations = new HashMap<Action, Animation>();
    }

    /**
     * Updates this EntityView, called by {@link PlayScene} each frame.
     */
    public abstract void render(SpriteBatch batch, float deltaTime);

    public T getModel() {
        return model;
    }

}
