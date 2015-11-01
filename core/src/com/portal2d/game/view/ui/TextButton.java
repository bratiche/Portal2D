package com.portal2d.game.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;

import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;


/**
 * TODO
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
    public void render(SpriteBatch batch) {
        batch.begin();

        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(batch, text, x, y);
        batch.end();
    }

    public boolean isClicked(float x, float y) {
        if(Gdx.input.isTouched()) {

            System.out.println( x + " , " + this.x+ " AND "+ y + " , " +this.y );
            System.out.println(x >= this.x - width && y >= this.y - width && x <= this.x + gl.width + width  && y <= this.y + gl.height + height);
            return x >= this.x - width && y >= this.y - gl.height - width && x <= this.x + gl.width + width  && y <= this.y + height;
        }
        return false;
    }

}
