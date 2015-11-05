package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.LevelLoader;
import com.portal2d.game.controller.PlayerController;
import com.portal2d.game.controller.save.GameSlot;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.view.scenes.PlayScene;

import static com.portal2d.game.model.ModelConstants.Box2D.*;

/**
 * Main state of the game.
 */
public class PlayState extends GameState {

    private World world;
    private Level level;
    private LevelLoader levelLoader;

    private PlayScene scene;
    private PlayerController playerController;
    private GameSlot slot;

    public PlayState(GameStateManager gsm, LevelName levelName, GameSlot slot) {
        super(gsm);
        this.slot = slot;

        // Create world and level loader
        world = new World(DEFAULT_GRAVITY, true);
        levelLoader = new LevelLoader(world, this);

        // Load level
        level = levelLoader.loadLevel(levelName);
    }

    @Override
    public void entered() {
        // Setup view
        scene = new PlayScene(world, level);

        // Setup controller
        playerController = new PlayerController(this, level);
    }

    @Override
    public void handleInput() {

        unproject(scene.getCamera());
        //pause the game
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.push(new PauseState(gsm, this));
        }

        // Back to menu
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            slot.save();
            gsm.set(new MenuState(gsm));
        }

        // Restart the level
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            //change level to the same level, which makes sense
            changeLevel(level.getLevelName());
        }

        //Update player input events
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

    // TESTEO
    private ShapeRenderer debugRenderer = new ShapeRenderer();

    @Override
    public void render(SpriteBatch batch) {
        scene.render(batch, mouse.x, mouse.y);

        //TESTEO
        playerController.drawPortalGunRayCast();
        playerController.drawGrabRange();

    }

    @Override
    public void leaving() {
        //world.dispose(); // -> this crashes the game
    }

    public void changeLevel(LevelName nextLevel) {

        // Unlock nextLevel and save the game
        nextLevel.setLocked(false);
        slot.save();

        // Remove all bodies
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for(int i = 0; i < bodies.size; i++) {
            Body body = bodies.get(i);
            world.destroyBody(body);
        }

        // Set new level
        level = levelLoader.loadLevel(nextLevel);
        scene.setLevel(level);
        playerController.setLevel(level);
        world.setGravity(DEFAULT_GRAVITY);
    }

    public OrthographicCamera getBox2DCamera() {
        return scene.getBox2DCamera();
    }

    public void add(Entity entity) {
        scene.addView(entity);
    }

    //TODO remove entity views
    public void remove(Entity entity) {
        scene.removeView(entity);
    }

    //TESTEO
    public ShapeRenderer getDebugRenderer() {
        return debugRenderer;
    }

}
