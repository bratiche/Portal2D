package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.ui.*;


import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class MenuScene extends Scene {

    private Texture background;

    //Buttons
    private TextButton playButton;
    private TextButton selectLevelButton;
    private TextButton instructionsButton;
    private TextButton exitButton;

    public MenuScene() {
        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // font settings
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal("core/assets/font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FORMAT_SIZE; // setting font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        // Creating buttons
        playButton = new TextButton(FIRST_BUTTONSTART_MENU, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Play", font);
        selectLevelButton = new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Select Level", font);
        instructionsButton = new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS*2, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Instructions", font);
        exitButton =new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS*3, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Exit", font);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        playButton.render(batch, mouseX, mouseY);
        selectLevelButton.render(batch, mouseX, mouseY);
        instructionsButton.render(batch, mouseX, mouseY);
        exitButton.render(batch, mouseX, mouseY);

    }

    public TextButton getSelectLevelButton() {
        return selectLevelButton;
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
