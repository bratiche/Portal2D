package com.portal2d.game.view.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

/**
 *
 */
public class Text extends UIComponent {

    private String text;
    private BitmapFont font;
    private GlyphLayout layout;

    // Normal text in screen
    public Text(float x, float y, String text, BitmapFont font) {
        super(x, y, 0, 0);
        this.font = font;
        this.text = text;
        layout = new GlyphLayout(font, text);
    }

    // Centered text in screen on y position
    public Text(float y, String text, BitmapFont font) {
        this(0, y, text, font);
        this.x = VIEWPORT_WIDTH / 2 - layout.width / 2;
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {
        batch.begin();
        font.setColor(Color.WHITE);
        font.draw(batch, text, x, y);
        batch.end();
    }

}
