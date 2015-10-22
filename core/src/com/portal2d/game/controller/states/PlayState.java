package com.portal2d.game.controller.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.PlayController;
import com.portal2d.game.controller.level.Level;
import com.portal2d.game.controller.level.LevelLoader;
import com.portal2d.game.view.PlayScene;

import static com.portal2d.game.controller.Box2DConstants.*;

/**
 *
 */
public class PlayState extends GameState {

    private World world;
    private Level level;
    private LevelLoader levelLoader;

    private PlayScene playScene;
    private PlayController playController;

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void entered() {
        world = new World(DEFAULT_GRAVITY, true);
        levelLoader = new LevelLoader();
        level = levelLoader.loadNextLevel(world);

        playScene = new PlayScene(world, level);
        playController = new PlayController(this, level);
    }

    @Override
    public void update(float dt) {
        playController.handleInput();
        world.step(dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        level.update();
    }

    @Override
    public void render(SpriteBatch batch) {

        playScene.render(batch);
    }

    @Override
    public void leaving() {
        world.dispose();
    }

    public void changeLevel() {
        world.dispose();
        world = new World(DEFAULT_GRAVITY, true);
        level = levelLoader.loadNextLevel(world);
        playScene.setLevel(level);
        playController.setLevel(level);
    }

    public OrthographicCamera getCamera() {
        return playScene.getCamera();
    }
}
