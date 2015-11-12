package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.view.scenes.PauseScene;

/**
 *
 */
public class PauseState extends GameState {

    // The state that is paused (usually the PlayState)
    private GameState state;

    private PauseScene scene;

    public PauseState(GameStateManager gsm, GameState state) {
        super(gsm);
        this.state = state;
    }

    @Override
    public void entered() {
        scene = new PauseScene();
    }

    @Override
    public void handleInput() {
        unproject(scene.getCamera());

        if(Gdx.input.isKeyJustPressed(Input.Keys.P) || scene.getContinueButton().isClicked(mouse.x, mouse.y)) {
            gsm.pop();
        }
        else if (scene.getInstructionsButton().isClicked(mouse.x, mouse.y)) {
            gsm.push(new InstructionState(gsm));
        }
        else if (scene.getExitButton().isClicked(mouse.x, mouse.y)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the paused state
        state.render(batch);

        // Render this state
        scene.render(batch, mouse.x, mouse.y);
    }

}
