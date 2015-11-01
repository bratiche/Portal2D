package com.portal2d.game.view.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by matias on 01/11/15.
 */
public class ExitButton extends TextButton {
    public ExitButton( float x, float y, float width, float height, String text, BitmapFont font) {
        super(x, y, width, height, text, font);
    }

    public ExitButton( float y, float width, float height, String text, BitmapFont font) {
        super(y, width, height, text, font);
    }
}