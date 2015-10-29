package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class MenuScene extends Scene {

    private Texture background;
    private OrthographicCamera camera;

    public MenuScene() {
        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
    }

}
