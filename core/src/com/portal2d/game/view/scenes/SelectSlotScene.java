package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.controller.save.GameSlot;
import com.portal2d.game.controller.states.SelectSlotState;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.ViewConstants;
import com.portal2d.game.view.ui.Text;
import com.portal2d.game.view.ui.TextButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.portal2d.game.view.ViewConstants.*;

/**
 * Visual representation of the {@link SelectSlotState}
 */
public class SelectSlotScene extends Scene {

    private Map<TextButton, GameSlot> slotButtons;
    private TextButton backButton;
    private Texture background;
    private Text title;

    public SelectSlotScene() {
        camera = new BoundedCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        background = Portal2D.assets.getTexture(ViewConstants.TextureName.MENU_BG);
        slotButtons = new HashMap<TextButton, GameSlot>();

        BitmapFont font = Portal2D.assets.getFont(ViewConstants.FontName.PORTAL);

        for(int i = 0; i < GameSlot.values().length; i++) {
            TextButton button = new TextButton(500 - 100 * i, GameSlot.values()[i].name, font);
            slotButtons.put(button, GameSlot.values()[i]);
        }

        title = new Text(650, "Select a slot", font, Color.GRAY);

        font = Portal2D.assets.getFont(FontName.PORTAL_33);
        backButton = new TextButton(BACK_BUTTON_X, BACK_BUTTON_Y, "Back", font);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        title.render(batch, mouseX, mouseY);

        for(TextButton button : slotButtons.keySet()) {
            button.render(batch, mouseX, mouseY);
        }

        backButton.render(batch, mouseX, mouseY);
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Set<Map.Entry<TextButton, GameSlot>> getSlotButtons() {
        return slotButtons.entrySet();
    }

}
