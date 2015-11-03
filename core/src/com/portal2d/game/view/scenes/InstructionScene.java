package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.ui.Text;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class InstructionScene extends Scene {

    private Texture background;
    private TextButton backButton;
    private Text description;

    public InstructionScene() {

        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new BoundedCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        BitmapFont font = Portal2D.assets.getFont(FontName.PORTAL_33);

        backButton = new TextButton(FIRST_BUTTON_INSTRUCTIONS_X,FIRST_BUTTON_INSTRUCTIONS_Y, "Back", font);
        description = new Text(INSTRUCTION_TEXT_POSITION_X, INSTRUCTION_TEXT_POSITION_Y, INSTRUCTIONS_TEXT, font, Color.GRAY);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        backButton.render(batch, mouseX, mouseY);
        description.render(batch, mouseX, mouseY);
    }

    public TextButton getBackButton() {
        return backButton;
    }

}
