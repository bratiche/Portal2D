package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class MenuScene extends Scene {

    private Texture background;
    private TextButton title;

    //Buttons
    private TextButton playButton;
    private TextButton instructionsButton;
    private TextButton exitButton;

    public MenuScene() {
        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new BoundedCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        BitmapFont font = Portal2D.assets.getFont(FontName.PORTAL);

        title = new TextButton(VIEWPORT_HEIGHT - 100, "PORTAL 2D", font);
        title.setColors(BLUE_PORTAL_COLOR, ORANGE_PORTAL_COLOR);

        BitmapFont font2 = Portal2D.assets.getFont(FontName.DINB);
        playButton = new TextButton(450, "PLAY", font2);
        exitButton = new TextButton(450 - SPACE_BETWEEN_BUTTONS * 2, "QUIT", font2);
        instructionsButton = new TextButton(450 - SPACE_BETWEEN_BUTTONS, "INSTRUCTIONS", font2);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        title.render(batch, mouseX, mouseY);
        playButton.render(batch, mouseX, mouseY);
        instructionsButton.render(batch, mouseX, mouseY);
        exitButton.render(batch, mouseX, mouseY);

    }


    public TextButton getInstructionsButton() {
        return instructionsButton;
    }

    public TextButton getPlayButton() {
        return playButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

}
