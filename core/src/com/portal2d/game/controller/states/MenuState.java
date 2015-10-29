package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch batch) {
        scene.render(batch);
    }

    @Override
    public void leaving() {

    }
}
