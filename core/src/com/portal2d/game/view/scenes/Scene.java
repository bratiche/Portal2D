package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 */
public abstract class Scene {

    protected OrthographicCamera camera;

    public abstract void render(SpriteBatch batch, float mouseX, float mouseY);

    public OrthographicCamera getCamera() {
        return camera;
    }

}
