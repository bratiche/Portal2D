package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.view.scenes.PauseScene;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class PauseState extends GameState {

    // The state that is paused
    private GameState state;

    private PauseScene scene;

    public PauseState(GameStateManager gsm, GameState state) {
        super(gsm);
        this.state = state;



    }

    @Override
    public void entered() {
        scene = new PauseScene();
        System.out.println("Game Paused");
    }

    @Override
    public void handleInput() {

        mouse.x= Gdx.input.getX();
        mouse.y= Gdx.input.getY();

        scene.getCamera().unproject(mouse);

        if(Gdx.input.isKeyJustPressed(Input.Keys.P) ||  scene.getContinuebutton().isClicked(mouse.x, mouse.y)) {
            gsm.pop();
        }

        else if (scene.getIntructionsbutton().isClicked(mouse.x, mouse.y)) {


        }

        else if (scene.getExitbutton().isClicked(mouse.x, mouse.y)) {
            Gdx.app.exit();
        }

    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void render(SpriteBatch batch) {
        mouse.x= Gdx.input.getX();
        mouse.y= Gdx.input.getY();

        scene.getCamera().unproject(mouse);

        // Render the paused state
        state.render(batch);

        // Render this state
        batch.setProjectionMatrix(scene.getCamera().combined);
        scene.getIntructionsbutton().render(batch, mouse.x, mouse.y);
        scene.getContinuebutton().render(batch, mouse.x, mouse.y);
        scene.getExitbutton().render(batch, mouse.x, mouse.y);

    }

    @Override
    public void leaving() {
        System.out.println("Game Unpaused");
    }

}
