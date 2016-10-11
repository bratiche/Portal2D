package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.portal2d.game.Portal2D;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.LevelLoader;
import com.portal2d.game.controller.PlayerController;
import com.portal2d.game.controller.save.GameSlot;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.entities.enemies.Turret;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.exceptions.UnknownEntityException;
import com.portal2d.game.model.interactions.RayCast;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.model.level.LevelObserver;
import com.portal2d.game.model.weapons.GravityGunQuery;
import com.portal2d.game.view.entities.*;
import com.portal2d.game.view.scenes.PlayScene;
import com.portal2d.game.view.weapons.PortalGunView;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.view.ViewConstants.PPM;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_HEIGHT;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

/**
 * Main state of the game.
 */
public class PlayState extends GameState implements LevelObserver {

    private Level level;
    private LevelLoader levelLoader;

    private GameSlot slot;
    private PlayScene scene;
    private PlayerController playerController;

    private Map<Entity, EntityView> entities; // Map for having easy access to the models and their views

    //debugging stuff
    private boolean debug = false;
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
        levelLoader = new LevelLoader();

        // Create view and controller
        scene = new PlayScene();
        playerController = new PlayerController(this);

        // Create debug renderer
        box2DDebugRenderer = new Box2DDebugRenderer();

        goToLevel(levelName);
    }

    @Override
    public void handleInput() {

        unproject(scene.getCamera());

        // Pause the game
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            gsm.push(new PauseState(gsm, this));
        }

        // Back to menu
        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            slot.save();
            //level.removeAllEntities(); //->crashes
            gsm.set(new MenuState(gsm));
        }

        // Restart the level
        else if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            restartLevel();
        }

        // Update player input events
        playerController.handleInput();

        //Toggle options
        if(Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            debug = !debug;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            render = !render;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            //followPlayer = !followPlayer;
        }

    }

    @Override
    public void update(float dt) {

        level.update(dt);

        if(level.getPlayer().isDead()) {
            restartLevel();
        }
        else if(level.isFinished()){
            level.removeAllEntities();
            goToLevel(level.getNextLevel());
        }

    }

    private boolean render = true;
    private boolean followPlayer = true;

    @Override
    public void render(SpriteBatch batch) {

        // Set camera to follow the player
        float x = mouse.x - VIEWPORT_WIDTH / 2;
        float y = mouse.y - VIEWPORT_HEIGHT / 2;

        if(followPlayer) {
            x = level.getPlayer().getPosition().x * PPM;
            y = level.getPlayer().getPosition().y * PPM;
        }

        scene.getCamera().setPosition(x + VIEWPORT_WIDTH / 8, y + VIEWPORT_HEIGHT / 4);
        scene.getCamera().update();

        if(render) {
            scene.render(batch, mouse.x, mouse.y);
        }

        scene.getBox2DCamera().setPosition((x + VIEWPORT_WIDTH / 8) / PPM, (y + VIEWPORT_HEIGHT / 4) / PPM);
        scene.getBox2DCamera().update();

        //draw box2d world (debug)
        if (debug) {
            //drawGrabRange();
            drawPortalGunRayCast();
            box2DDebugRenderer.render(level.getWorld(), scene.getBox2DCamera().combined);
        }
    }

    @Override
    public void leaving() {
        // Sets back the new cursor
        Gdx.graphics.setCursor(null);
    }

    private void restartLevel() {
        level.removeAllEntities();
        goToLevel(level.getLevelName());
    }

    private void goToLevel(LevelName nextLevel) {

        // Unlock the level and save the game
        nextLevel.setLocked(false);
        slot.save();

        // Set new level
        scene.clearViews();
        level = levelLoader.loadLevel(nextLevel, this);
        scene.setTiledMap(Portal2D.assets.getTiledMap(level.getLevelName()));
        playerController.setLevel(level);
    }

    public OrthographicCamera getBox2DCamera() {
        return scene.getBox2DCamera();
    }

    //TESTEO
    private ShapeRenderer debugRenderer = new ShapeRenderer();

    //TESTEO
    public void drawPortalGunRayCast() {
        debugRenderer.setProjectionMatrix(getBox2DCamera().combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

        RayCast rayCast = level.getPlayer().getPortalGun().getRayCast();
        Vector2 beginPoint = rayCast.getBeginPoint();
        Vector2 endPoint = rayCast.getEndPoint();

        debugRenderer.line(beginPoint, endPoint);
        debugRenderer.end();
    }

    //TESTEO
    public void drawGrabRange() {
        GravityGunQuery query = level.getPlayer().getPortalGun().getQuery();
        query.updateAABB();

        float lowerX = query.getLowerX();
        float lowerY = query.getLowerY();
        float upperX = query.getUpperX();
        float upperY = query.getUpperY();

        debugRenderer.setProjectionMatrix(getBox2DCamera().combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.rect(lowerX, lowerY, upperX - lowerX, upperY - lowerY);
        debugRenderer.end();
    }

    @Override
    public void entityAdded(Entity entity) {

        switch (entity.getType()) {
            case PLAYER:
                PlayerView playerView = new PlayerView((Player) entity);
                PortalGunView portalGunView = new PortalGunView(((Player)entity).getPortalGun());
                entities.put(entity, playerView);
                scene.addView(playerView, portalGunView);
                break;
            case PORTAL:
                PortalView portalView = new PortalView((Portal) entity);
                entities.put(entity, portalView);
                scene.addView(portalView);
                break;
            case BOX:
                BoxView boxView = new BoxView((Box) entity);
                scene.addView(boxView);
                entities.put(entity, boxView);
                break;
            case EXIT:
                ExitView exitView = new ExitView((Exit) entity);
                scene.addView(exitView);
                entities.put(entity, exitView);
                break;
            case GATE:
                GateView gateView = new GateView((Gate) entity);
                scene.addView(gateView);
                entities.put(entity, gateView);
                break;
            case BUTTON:
                ButtonView buttonView = new ButtonView((Button) entity);
                scene.addView(buttonView);
                entities.put(entity, buttonView);
                break;
            case BULLET:
                BulletView bulletView = new BulletView((Bullet) entity);
                scene.addView(bulletView);
                entities.put(entity, bulletView);
                break;
            case TURRET:
                TurretView turretView = new TurretView((Turret) entity);
                scene.addView(turretView);
                entities.put(entity, turretView);
                break;
            case ACID:
                AcidView acidView = new AcidView((Acid) entity);
                scene.addView(acidView);
                entities.put(entity, acidView);
                break;
            case SURFACE:
                //System.out.println("No surface view");
                break;
            case PORTABLE_SURFACE:
                //System.out.println("No portable surface view");
                break;
            default:
                throw new UnknownEntityException(entity.getType());
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
        scene.removeView(entities.get(entity));
        entities.remove(entity);
    }

}
