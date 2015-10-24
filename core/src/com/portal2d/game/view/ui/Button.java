package com.portal2d.game.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 */
public class Button extends UIComponent {

    private String text;
    private BitmapFont font;

    @Override
    public void render(SpriteBatch batch) {

    }

    public boolean isClicked() {
        if(Gdx.input.isTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.input.getY();

            return x >= this.x && y >= this.y && x <= this.x + width && y <= this.y + height;
        }
        return false;
    }

}
