package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.controller.states.SelectLevelState;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.ui.Text;
import com.portal2d.game.view.ui.TextButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.portal2d.game.view.ViewConstants.*;

/**
 * Visual representation of the {@link SelectLevelState}
 */
public class SelectLevelScene extends Scene {

    private Map<TextButton, LevelName> levelButtons;
    private TextButton backButton;
    private Text title;
    private Texture background;

    public SelectLevelScene() {

        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new BoundedCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        BitmapFont font = Portal2D.assets.getFont(FontName.DINB_33);

        title = new Text(650, "LEVELS", font, Color.GRAY);

        // Create buttons for each level
        levelButtons = new HashMap<TextButton, LevelName>();

        for(int i = 0; i < LevelName.values().length; i++) {
            LevelName levelName = LevelName.values()[i];
            TextButton button = new TextButton(400, 575 - 50 * i, "0" + (i + 1), font);
            levelButtons.put(button, LevelName.values()[i]);
            if(levelButtons.get(button).isLocked()) {
                button.appendText("  -LOCKED");
                button.setColors(Color.DARK_GRAY);
            }
            else {
                button.appendText("  -" + levelName.getName());
            }
        }

        font = Portal2D.assets.getFont(FontName.PORTAL_33);
        backButton = new TextButton(100, 100, "Back", font);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        for(TextButton button : levelButtons.keySet()) {
            button.render(batch, mouseX, mouseY);
        }
        backButton.render(batch, mouseX, mouseY);
        title.render(batch, mouseX, mouseY);
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Set<Map.Entry<TextButton, LevelName>> getLevelButtons() {
        return levelButtons.entrySet();
    }
}
