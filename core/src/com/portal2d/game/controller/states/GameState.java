package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.view.scenes.Scene;

/**
 * A game state is a controller for the flow of the game.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    //view
    protected Scene scene;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void entered();

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch batch);

    public abstract void leaving();
}
