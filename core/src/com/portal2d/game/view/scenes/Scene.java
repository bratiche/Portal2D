package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.states.GameState;
import com.portal2d.game.view.BoundedCamera;

/**
 * Visual representation of a {@link GameState}
 */
public abstract class Scene {

    protected BoundedCamera camera;

    //public abstract void create();

    public abstract void render(SpriteBatch batch, float mouseX, float mouseY);

    public BoundedCamera getCamera() {
        return camera;
    }

}
