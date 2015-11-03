package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
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

    //Buttons
    private TextButton playButton;
    private TextButton instructionsButton;
    private TextButton exitButton;

    public MenuScene() {
        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new BoundedCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        BitmapFont font = Portal2D.assets.getFont(FontName.FONT_80);

        // Creating buttons
        playButton = new TextButton(FIRST_BUTTONSTART_MENU, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Play", font);
        instructionsButton = new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Instructions", font);
        exitButton = new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS*2, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Exit", font);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

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
