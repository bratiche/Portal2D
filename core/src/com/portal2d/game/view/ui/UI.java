package com.portal2d.game.view.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UI {

    //private List<UIComponent> components;
    private List<Button> buttons;

    public UI() {
        buttons = new ArrayList<Button>();
    }

    public void render(SpriteBatch batch) {
        for(Button button : buttons) {
            button.render(batch);
        }
    }

    public void add(Button button) {
        buttons.add(button);
    }

}
