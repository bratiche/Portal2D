package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
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
        level = levelLoader.loadLevel(LevelName.TEST_LEVEL);

        scene = new PlayScene(world, level);
        playerController = new PlayerController(this, level);
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

        //restart the level
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            //change level to the same level, which makes sense
            changeLevel(level.getLevelName());
        }

        //controller for the player
        playerController.handleInput();
    }

    @Override
    public void update(float dt) {

        //Physics update
        world.step(dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

        //States/interactions update
        level.update();

        if(level.isFinished()) {
            changeLevel(level.getNextLevel());
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        scene.render(batch);
    }

    @Override
    public void leaving() {
        world.dispose();
    }

    public void changeLevel(LevelName nextLevel) {

        //remove all bodies
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for(int i = 0; i < bodies.size; i++) {
            Body body = bodies.get(i);
            world.destroyBody(body);
        }

        //set new level
        level = levelLoader.loadLevel(nextLevel);
        scene.setLevel(level);
        playerController.setLevel(level);
    }

    public OrthographicCamera getBox2DCamera() {
        return scene.getBox2DCamera();
    }
}
