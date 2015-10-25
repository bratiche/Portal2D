package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;

/**
 * A game state is a controller for the flow of the game.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    /**
     * Called right after this state is pushed into the game.
     */
    public abstract void entered();

    /**
     * Handles this state's input events.
     */
    public abstract void handleInput();

    /**
     * Updates the state's logic, if there is any.
     * @param dt time passed since last frame (delta time)
     */
    public abstract void update(float dt);

    /**
     * Draws the state scene into the screen.
     * @param batch the SpriteBatch used to draw.
     */
    public abstract void render(SpriteBatch batch);

    /**
     * Called right before this state is finished.
     */
    public abstract void leaving();
}
