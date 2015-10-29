package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;

import static com.portal2d.game.view.ViewConstants.VIEWPORT_HEIGHT;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

/**
 *
 */
public class PauseState extends GameState {

    // The state that is paused
    private GameState state;

    //TODO: move to view
    private Sprite sprite;
    private OrthographicCamera camera;

    public PauseState(GameStateManager gsm, GameState state) {
        super(gsm);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        this.state = state;
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/badlogic.jpg")));
        sprite.setCenter(600, 360);
    }

    @Override
    public void entered() {
        System.out.println("Game Paused");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.pop();
        }
    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the paused state
        state.render(batch);

        // Render this state
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.rotate(1);
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void leaving() {
        System.out.println("Game Unpaused");
    }

}
