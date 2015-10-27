package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.Entity;

/**
 * Base class for drawing entities, it takes a generic parameter in the constructor
 * indicating the type of the model to draw.
 */
public abstract class EntityView<T extends Entity> {

    protected T model;
    protected Body body;

    //Animation, etc

    public EntityView(T model) {
        this.model = model;
        body = model.getBody();
    }

    public abstract void render(SpriteBatch batch);

}
