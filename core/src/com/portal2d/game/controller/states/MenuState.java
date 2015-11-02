package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.view.scenes.MenuScene;

/**
 *
 */
public class MenuState extends GameState {

    private MenuScene scene;

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void entered() {
        scene = new MenuScene();
    }

    @Override
    public void handleInput() {
        unproject(scene.getCamera());

        if (scene.getInstructionsButton().isClicked(mouse.x, mouse.y)) {
            gsm.push(new InstructionState(gsm));
        }
        else if (scene.getSelectLevelButton().isClicked(mouse.x, mouse.y)) {
            gsm.push(new SelectLevelState(gsm));
        }
        else if ( scene.getPlayButton().isClicked(mouse.x, mouse.y)) {
            gsm.set(new PlayState(gsm, LevelName.TEST_LEVEL));
        }
        else if (scene.getExitButton().isClicked(mouse.x, mouse.y)) {
            Gdx.app.exit();
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
