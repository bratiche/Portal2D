package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.view.ui.TextButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class SelectLevelScene extends Scene {

    private Map<TextButton, LevelName> levelButtons;
    private TextButton backButton;
    private Texture background;

    public SelectLevelScene() {

        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        //TODO: font map in Assets
        // font setting
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/font/font.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = INSTRUCTION_SIZE; // setting font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        backButton = new TextButton(FIRST_BUTTON_INSTRUCTIONS_X,FIRST_BUTTON_INSTRUCTIONS_Y, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Back", font);

        // Create buttons for each level
        levelButtons = new HashMap<TextButton, LevelName>();

        for(int i = 0; i < LevelName.values().length; i++) {
            LevelName levelName = LevelName.getLevelName(i);
            TextButton button = new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS * i, TEXTBUTTON_WIDTH,
                                TEXTBUTTON_HEIGHT, levelName.toString(), font);
            levelButtons.put(button, LevelName.getLevelName(i));
        }
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        backButton.render(batch, mouseX, mouseY);
        for(TextButton button : levelButtons.keySet()) {
            button.render(batch, mouseX, mouseY);
        }
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Set<Map.Entry<TextButton, LevelName>> getLevelButtons() {
        return levelButtons.entrySet();
    }
}
