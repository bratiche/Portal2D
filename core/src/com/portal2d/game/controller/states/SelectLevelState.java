package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.view.scenes.SelectLevelScene;
import com.portal2d.game.view.ui.TextButton;

import java.util.Map;

/**
 *
 */
public class SelectLevelState extends GameState {

    private SelectLevelScene scene;

    public SelectLevelState(GameStateManager gsm) {
        super(gsm);
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

        for(Map.Entry<TextButton, LevelName> entry: scene.getLevelButtons()) {
            TextButton button = entry.getKey();

            if(button.isClicked(mouse.x, mouse.y)) {
                LevelName levelName = entry.getValue();
                if(!levelName.isLocked()) {
                    gsm.set(new PlayState(gsm, levelName));
                }
            }
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch batch) {
        scene.render(batch, mouse.x, mouse.y);
    }

    @Override
    public void leaving() {

    }
}
