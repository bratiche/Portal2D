package com.portal2d.game.view.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by matias on 01/11/15.
 */
public class LoadButton extends TextButton {
    public LoadButton(float x, float y, float width, float height, String text, BitmapFont font) {
        super(x, y, width, height, text, font);
    }

    public LoadButton(float y, float width, float height, String text, BitmapFont font) {
        super(y, width, height, text, font);
    }
}