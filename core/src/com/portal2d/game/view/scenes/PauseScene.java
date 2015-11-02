package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
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
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        BitmapFont font = Portal2D.assets.getFont(FontName.FONT_80);


        continueButton =new TextButton(FIRST_BUTTON_START_PAUSE, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Continue", font);
        instructionsButton =new TextButton(FIRST_BUTTON_START_PAUSE - SPACE_BETWEEN_BUTTONS, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Instructions", font);
        exitButton =new TextButton(FIRST_BUTTON_START_PAUSE - SPACE_BETWEEN_BUTTONS*2, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Exit", font);
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
