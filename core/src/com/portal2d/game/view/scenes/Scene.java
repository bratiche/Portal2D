package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.view.ui.UIComponent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Scene {

    protected List<UIComponent> components;

    public Scene() {
        components = new ArrayList<UIComponent>();
    }

    public void render(SpriteBatch batch) {

    }

}
