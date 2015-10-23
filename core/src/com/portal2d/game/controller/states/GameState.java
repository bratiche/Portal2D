package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.StateController;

/**
 *
 */
public abstract class GameState {

    protected GameStateManager gsm;
    protected StateController controller;

    //view
    protected Stage stage;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void entered();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);

    public abstract void leaving();
}
