package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.LevelLoader;
import com.portal2d.game.controller.PlayerController;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.view.scenes.PlayScene;

import static com.portal2d.game.controller.Box2DConstants.*;

/**
 *
 */
public class PlayState extends GameState {

    private World world;
    private Level level;
    private LevelLoader levelLoader;

    private PlayScene scene;
    private PlayerController playerController;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void entered() {
        world = new World(DEFAULT_GRAVITY, true);
        levelLoader = new LevelLoader(world);
        level = levelLoader.loadNextLevel();

        scene = new PlayScene(world, level);
        playerController = new PlayerController(this, level);

        //test
        System.out.println(LevelName.getLevelName(0));
    }

    @Override
    public void handleInput() {

        //pause the game
//        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
//            gsm.push(new PauseState());
//        }

        //back to menu
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        //controller for the player
        playerController.handleInput();

        //Physics update
        world.step(dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

        //States/interactions update
        level.update();

    }

    @Override
    public void render(SpriteBatch batch) {
        scene.render(batch);
    }

    @Override
    public void leaving() {
        world.dispose();
    }

    public void changeLevel() {
        world.dispose();
        world = new World(DEFAULT_GRAVITY, true);
        level = levelLoader.loadNextLevel();
        scene.setLevel(level);
        playerController.setLevel(level);
    }

    public OrthographicCamera getCamera() {
        return scene.getCamera();
    }
}
