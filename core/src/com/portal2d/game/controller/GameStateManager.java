package com.portal2d.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.states.GameState;

import java.util.Stack;

/**
 *
 */
public class GameStateManager {

    private Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<GameState>();
    }

    public void push(GameState state) {
        states.push(state);
        state.entered();
    }

    public GameState pop() {
        GameState state = states.pop();
        state.leaving();
        return state;
    }

    public void set(GameState state) {
        pop();
        push(state);
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public void render(SpriteBatch batch) {
        states.peek().render(batch);
    }
}
