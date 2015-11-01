package com.portal2d.game.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;


/**
 * TODO: change color when cursor is on the text
 */
public class TextButton extends UIComponent {

    private String text;
    private BitmapFont font;
    private GlyphLayout gl;

    // Normal button in screen
    public TextButton ( float x, float y, float width, float height, String text, BitmapFont font) {
        super(x, y, width, height);
        this.font=font;
        this.text=text;
        gl = new GlyphLayout(font, text);
    }

    // Centered button in screen on y position
    public TextButton ( float y, float width, float height, String text, BitmapFont font) {
        this(0, y, width, height, text, font);
        this.x= VIEWPORT_WIDTH/2 - gl.width/2;

    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.begin();
        if(x >= this.x - width && y >= this.y - gl.height - width && x <= this.x + gl.width + width  && y <= this.y + height) {
            font.setColor(Color.BLACK);
        }
        else {
            font.setColor(Color.WHITE);
        }

        font.draw(batch, text, this.x, this.y);
        batch.end();
    }

    public boolean isClicked(float x, float y) {

            return Gdx.input.isTouched() && x >= this.x - width && y >= this.y - gl.height - width && x <= this.x + gl.width + width  && y <= this.y + height;
    }

}
