package com.portal2d.game.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;


/**
 *
 */
public class TextButton extends UIComponent {

    private String text;
    private BitmapFont font;
    private GlyphLayout layout;

    // Normal button in screen
    public TextButton(float x, float y, float width, float height, String text, BitmapFont font) {
        super(x, y, width, height);
        this.font = font;
        setText(text);
    }

    // Centered button in screen on y position
    public TextButton(float y, float width, float height, String text, BitmapFont font) {
        this(0, y, width, height, text, font);
        this.x = VIEWPORT_WIDTH / 2 - layout.width / 2;
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {
        batch.begin();
        if(contains(mouseX, mouseY)) {
            font.setColor(Color.BLACK);
        }
        else {
            font.setColor(Color.WHITE);
        }

        font.draw(batch, text, x, y);
        batch.end();
    }

    public boolean isClicked(float x, float y) {
        return Gdx.input.justTouched() && contains(x, y);
    }

    private boolean contains(float x, float y) {
        return  x >= this.x - width && y >= this.y - layout.height - width
                && x <= this.x + layout.width + width && y <= this.y + height;
    }
    
    public void setText(String text) {
        this.text = text;
        layout = new GlyphLayout(font, text);
    }

    public void appendText(String text) {
        setText(this.text + text);
    }

}
