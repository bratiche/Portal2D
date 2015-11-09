package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.controller.states.PauseState;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;

/**
 * Visual representation of the {@link PauseState}
 */
public class PauseScene extends Scene {

    private TextButton instructionsButton;
    private TextButton continueButton;
    private TextButton exitButton;

    public PauseScene() {
        camera = new BoundedCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        BitmapFont font = Portal2D.assets.getFont(FontName.PORTAL);

        int startY = 500;

        continueButton = new TextButton(startY, "Continue", font);
        continueButton.setColors(Color.DARK_GRAY, Color.LIGHT_GRAY);
        instructionsButton = new TextButton(startY - SPACE_BETWEEN_BUTTONS, "Instructions", font);
        instructionsButton.setColors(Color.DARK_GRAY, Color.LIGHT_GRAY);
        exitButton = new TextButton(startY - SPACE_BETWEEN_BUTTONS * 2, "Exit", font);
        exitButton.setColors(Color.DARK_GRAY, Color.LIGHT_GRAY);
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
