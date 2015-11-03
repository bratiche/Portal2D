package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.save.GameSlot;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.view.scenes.SelectLevelScene;
import com.portal2d.game.view.ui.TextButton;

import java.util.Map;

/**
 *
 */
public class SelectLevelState extends GameState {

    private SelectLevelScene scene;
    private GameSlot slot;

    /**
     * Receives the {@link GameSlot} where this game is stored and loads it.
     */
    public SelectLevelState(GameStateManager gsm, GameSlot slot) {
        super(gsm);
        this.slot = slot;
        slot.load();
    }

    @Override
    public void entered() {
        scene = new SelectLevelScene();
    }

    @Override
    public void handleInput() {
        unproject(scene.getCamera());

        if(scene.getBackButton().isClicked(mouse.x, mouse.y)) {
            gsm.pop();
        }

        else {
            for (Map.Entry<TextButton, LevelName> entry : scene.getLevelButtons()) {
                TextButton button = entry.getKey();

                if (button.isClicked(mouse.x, mouse.y)) {
                    LevelName levelName = entry.getValue();
                    if (!levelName.isLocked()) {
                        gsm.set(new PlayState(gsm, levelName, slot));
                    }
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
