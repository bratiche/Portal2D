package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.Portal2D;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.LevelLoader;
import com.portal2d.game.controller.PlayerController;
import com.portal2d.game.controller.save.GameSlot;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.entities.enemies.Turret;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.view.entities.*;
import com.portal2d.game.view.scenes.PlayScene;
import com.portal2d.game.view.weapons.PortalGunView;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.model.ModelConstants.Box2D.*;

/**
 * Main state of the game.
 */
public class PlayState extends GameState {

    private World world;
    private Level level;
    private LevelLoader levelLoader;

    private GameSlot slot;
    private PlayScene scene;
    private PlayerController playerController;

    private Map<Entity, EntityView> entities; // Map for having easy access to the models and their views

    //debugging stuff
    private boolean debug = true;
    private Box2DDebugRenderer box2DDebugRenderer;

    /**
     * Starts a new game.
     * @param levelName the Level to start the game.
     * @param slot the GameSlot to save the game.
     */
    public PlayState(GameStateManager gsm, LevelName levelName, GameSlot slot) {
        super(gsm);
        this.slot = slot;

        entities = new HashMap<Entity, EntityView>();

        // Create world and level loader
        world = new World(DEFAULT_GRAVITY, true);
        levelLoader = new LevelLoader(world, this);

        // Create view and controller
        scene = new PlayScene();
        playerController = new PlayerController(this);

        // Create debug renderer
        box2DDebugRenderer = new Box2DDebugRenderer();

        goToLevel(levelName);
    }

    @Override
    public void entered() {
        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void handleInput() {

        unproject(scene.getCamera());

        // Pause the game
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.push(new PauseState(gsm, this));
        }

        // Back to menu
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            slot.save();
            level.removeAllEntities();
            gsm.set(new MenuState(gsm));
        }

        // Restart the level
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            restartLevel();
        }

        // Update player input events
        playerController.handleInput();

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            debug = !debug;
            Gdx.input.setCursorCatched(!debug);
        }

    }

    @Override
    public void update(float dt) {

        // Physics update
        world.step(dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

        // States/interactions update
        level.update();

        if(level.getPlayer().isDead()) {
            restartLevel();
        }
        else if(level.isFinished()){
            level.removeAllEntities();
            goToLevel(level.getNextLevel());
        }

    }

    private void restartLevel() {
        level.removeAllEntities();
        goToLevel(level.getLevelName());
    }

    // TEST
    private ShapeRenderer debugRenderer = new ShapeRenderer();

    // TEST
    public ShapeRenderer getDebugRenderer() {
        return debugRenderer;
    }

    @Override
    public void render(SpriteBatch batch) {
        scene.render(batch, mouse.x, mouse.y);

        // TEST
        playerController.drawPortalGunRayCast();
        playerController.drawGrabRange();

        //draw box2d world (debug)
        if (debug) {
            box2DDebugRenderer.render(world, scene.getBox2DCamera().combined);
        }

    }

    @Override
    public void leaving() {
        Gdx.input.setCursorCatched(false);
        //world.dispose(); // -> this crashes the game
    }

    private void goToLevel(LevelName nextLevel) {

        // Unlock the level and save the game
        nextLevel.setLocked(false);
        slot.save();

        // Set new level
        scene.clearViews();
        level = levelLoader.loadLevel(nextLevel);
        scene.setTiledMap(Portal2D.assets.getTiledMap(level.getLevelName()));
        playerController.setLevel(level);
    }



    public OrthographicCamera getBox2DCamera() {
        return scene.getBox2DCamera();
    }

    public void add(Box box) {
        BoxView boxView = new BoxView(box);
        scene.addView(boxView);
        entities.put(box, boxView);
    }

    public void add(Exit exit) {
        ExitView exitView = new ExitView(exit);
        scene.addView(exitView);
        entities.put(exit, exitView);
    }

    public void add(Gate gate) {
        GateView gateView = new GateView(gate);
        scene.addView(gateView);
        entities.put(gate, gateView);
    }

    public void add(Portal portal) {
        PortalView portalView = new PortalView(portal);
        scene.addView(portalView);
        entities.put(portal, portalView);
    }

    public void add(Player player) {
        PlayerView playerView = new PlayerView(player);
        PortalGunView portalGunView = new PortalGunView(player.getPortalGun());
        scene.addView(playerView, portalGunView);
        entities.put(player, playerView);
    }

    public void add(Button button) {
        ButtonView buttonView = new ButtonView(button);
        scene.addView(new ButtonView(button));
        entities.put(button, buttonView);
    }

    public void add(Bullet bullet) {
        BulletView bulletView = new BulletView(bullet);
        scene.addView(bulletView);
        entities.put(bullet, bulletView);
    }

    public void add(Turret turret) {
        TurretView turretView = new TurretView(turret);
        scene.addView(turretView);
        entities.put(turret, turretView);
    }

    public void add(Acid acid) {
        AcidView acidView = new AcidView(acid);
        scene.addView(new AcidView(acid));
        entities.put(acid, acidView);
    }

    public void remove(Entity entity) {
        scene.removeView(entities.get(entity));
    }

}
