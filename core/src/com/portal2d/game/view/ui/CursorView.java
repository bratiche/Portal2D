package com.portal2d.game.view.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * TODO use libgdx set cursor method
 */
public class CursorView {

    private TextureRegion texture;
    private int width;
    private int height;

    public CursorView(TextureRegion texture) {
        setTexture(texture);
    }

    public void render(SpriteBatch batch, float mouseX, float mouseY) {
        batch.begin();
        batch.draw(texture, mouseX - width / 2, mouseY - height / 2);
        batch.end();
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
        width = texture.getRegionWidth();
        height = texture.getRegionHeight();
    }

}
