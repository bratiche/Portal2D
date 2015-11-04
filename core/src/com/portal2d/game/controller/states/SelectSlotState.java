package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.save.GameSlot;
import com.portal2d.game.view.ui.Text;
import com.portal2d.game.view.ui.TextButton;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class SelectSlotState extends GameState {

    //TODO: move to view ?
    private Map<TextButton, GameSlot> slotButtons;
    private TextButton backButton;
    private OrthographicCamera camera;
    private Texture background;
    private Text title;
    //////////////////////

    public SelectSlotState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void entered() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        slotButtons = new HashMap<TextButton, GameSlot>();

        BitmapFont font = Portal2D.assets.getFont(FontName.PORTAL);

        for(int i = 0; i < GameSlot.values().length; i++) {
            TextButton button = new TextButton(FIRST_BUTTONSTART_MENU - 100 - SPACE_BETWEEN_BUTTONS * i,
                    GameSlot.values()[i].name, font);
            slotButtons.put(button, GameSlot.values()[i]);
        }
        title = new Text(650, "Select a slot", font, Color.GRAY);

        font = Portal2D.assets.getFont(FontName.PORTAL_33);
        backButton = new TextButton(FIRST_BUTTON_INSTRUCTIONS_X,FIRST_BUTTON_INSTRUCTIONS_Y, "Back", font);
    }

    @Override
    public void handleInput() {
        unproject(camera);

        if(backButton.isClicked(mouse.x, mouse.y)) {
            gsm.pop();
        }

        else {
            for(Map.Entry<TextButton, GameSlot> entry : slotButtons.entrySet()) {
                TextButton button = entry.getKey();

                if (button.isClicked(mouse.x, mouse.y)) {
                    GameSlot slot = entry.getValue();
                    gsm.push(new SelectLevelState(gsm, slot));
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        title.render(batch, mouse.x, mouse.y);

        for(TextButton button : slotButtons.keySet()) {
            button.render(batch, mouse.x, mouse.y);
        }

        backButton.render(batch, mouse.x, mouse.y);
    }

    @Override
    public void leaving() {

    }
}
