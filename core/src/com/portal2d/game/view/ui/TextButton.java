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
    private Color color1 = Color.GRAY;
    private Color color2 = Color.WHITE;

    /**
     * Creates a Button with the dimensions of the text and font.
     * @see #setText(String)
     */
    public TextButton(float x, float y, String text, BitmapFont font) {
        this.x = x;
        this.y = y;
        this.font = font;
        setText(text);
    }

    /**
     * Centered button on x position.
     */
    public TextButton(float y, String text, BitmapFont font) {
        this(0, y, text, font);
        this.x = VIEWPORT_WIDTH / 2 - width / 2;
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {
        batch.begin();
        if(contains(mouseX, mouseY)) {
            font.setColor(color2);
        }
        else {
            font.setColor(color1);
        }

        font.draw(batch, text, x, y);
        batch.end();
    }

    public boolean isClicked(float x, float y) {
        return Gdx.input.justTouched() && contains(x, y);
    }

    private boolean contains(float x, float y) {
        return x >= this.x && x <= this.x + width && y >= this.y - height && y <= this.y;
    }
    
    public void setText(String text) {
        this.text = text;
        GlyphLayout layout = new GlyphLayout(font, text);
        width = layout.width;
        height = layout.height;
    }

    public void appendText(String text) {
        setText(this.text + text);
    }

    public void setColors(Color color) {
        setColors(color, color);
    }

    public void setColors(Color color1, Color color2) {
        setColor1(color1);
        setColor2(color2);
    }

    public void setColor1(Color color) {
        this.color1 = color;
    }

    public void setColor2(Color color) {
        this.color2 = color;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

}
