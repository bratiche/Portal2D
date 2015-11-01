package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.view.scenes.MenuScene;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.PPM;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_HEIGHT;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

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

        mouse.x= Gdx.input.getX();
        mouse.y= Gdx.input.getY();

        scene.getCamera().unproject(mouse);


        for ( TextButton t : scene.getButtons()) {
            if(t.isClicked(mouse.x, mouse.y)) {
                gsm.set(new PlayState(gsm));
            }
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
