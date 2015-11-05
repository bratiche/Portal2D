package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.save.GameSlot;
import com.portal2d.game.view.scenes.SelectSlotScene;
import com.portal2d.game.view.ui.TextButton;

import java.util.Map;

/**
 *
 */
public class SelectSlotState extends GameState {

    private SelectSlotScene scene;

    public SelectSlotState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void entered() {
        scene = new SelectSlotScene();
    }

    @Override
    public void handleInput() {
        unproject(scene.getCamera());

        if(scene.getBackButton().isClicked(mouse.x, mouse.y)) {
            gsm.pop();
        }

        else {
            for(Map.Entry<TextButton, GameSlot> entry : scene.getSlotButtons()) {
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
        scene.render(batch, mouse.x, mouse.y);
    }

    @Override
    public void leaving() {

    }
}
