package com.portal2d.game.controller;

import com.portal2d.game.controller.states.GameState;

/**
 *
 */
public abstract class StateController {
    protected GameState state;

    public StateController(GameState state) {
        this.state = state;
    }

    public abstract void handleInput();
}
