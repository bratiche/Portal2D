package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class PauseScene extends Scene {

    private TextButton instructionsButton;
    private TextButton continueButton;
    private TextButton exitButton;

    public PauseScene() {
        camera = new BoundedCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        BitmapFont font = Portal2D.assets.getFont(FontName.PORTAL);

        continueButton = new TextButton(FIRST_BUTTON_START_PAUSE, "Continue", font);
        instructionsButton = new TextButton(FIRST_BUTTON_START_PAUSE - SPACE_BETWEEN_BUTTONS, "Instructions", font);
        exitButton = new TextButton(FIRST_BUTTON_START_PAUSE - SPACE_BETWEEN_BUTTONS*2, "Exit", font);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        batch.setProjectionMatrix(camera.combined);

        continueButton.render(batch, mouseX, mouseY);
        instructionsButton.render(batch, mouseX, mouseY);
        exitButton.render(batch, mouseX, mouseY);
    }

    public TextButton getInstructionsButton() {
        return instructionsButton;
    }

    public TextButton getContinueButton() {
        return continueButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

}
