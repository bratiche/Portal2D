package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

        BitmapFont font = Portal2D.assets.getFont(FontName.FONT_40);

        for(int i = 0; i < GameSlot.values().length; i++) {
            TextButton button = new TextButton(FIRST_BUTTONSTART_MENU - 100 - SPACE_BETWEEN_BUTTONS * i, TEXTBUTTON_WIDTH,
                    TEXTBUTTON_HEIGHT, GameSlot.values()[i].name, font);
            slotButtons.put(button, GameSlot.values()[i]);
        }

        font = Portal2D.assets.getFont(FontName.FONT_80);
        title = new Text(650, "Select a slot", font);
    }

    @Override
    public void handleInput() {
        unproject(camera);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gsm.pop();
        }

        else {
            for(Map.Entry<TextButton, GameSlot> entry : slotButtons.entrySet()) {
                TextButton button = entry.getKey();

                if (button.isClicked(mouse.x, mouse.y)) {
                    GameSlot slot = entry.getValue();
                    gsm.set(new SelectLevelState(gsm, slot));
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        title.render(batch, mouse.x, mouse.y);

        for(TextButton button : slotButtons.keySet()) {
            button.render(batch, mouse.x, mouse.y);
        }
    }

    @Override
    public void leaving() {

    }
}
