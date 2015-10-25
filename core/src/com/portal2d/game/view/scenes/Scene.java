package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.view.ui.UI;

/**
 *
 */
public abstract class Scene {

    protected UI ui;

    public Scene() {
        ui = new UI();
    }

    public abstract void render(SpriteBatch batch);

}
