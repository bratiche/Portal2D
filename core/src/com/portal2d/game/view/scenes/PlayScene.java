package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.entities.*;

import java.util.HashSet;
import java.util.Set;

import static com.portal2d.game.view.ViewConstants.*;

/**
 * Visual representation of the {@link Level}.
 */
public class PlayScene extends Scene {

    private Level level;

    private OrthogonalTiledMapRenderer tmr;

    private Set<EntityView> entityViews;

    // Separated set for drawing buttons, this is to make sure the buttons are drawn on top of the other entities
    private Set<ButtonView> buttonViews;

    private PlayerView playerView;

    //debugging stuff
    private boolean debug = true;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private BoundedCamera box2DCamera;

    public PlayScene(World world, Level level) {
        this.world = world;

        debugRenderer = new Box2DDebugRenderer();

        entityViews = new HashSet<EntityView>();

        buttonViews = new HashSet<ButtonView>();

        setLevel(level);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        //set camera to follow the player
        float x = level.getPlayer().getBody().getPosition().x;
        float y = level.getPlayer().getBody().getPosition().y;

        camera.setPosition(x * PPM + VIEWPORT_WIDTH / 8, y * PPM + VIEWPORT_HEIGHT / 4);
        camera.update();

        //draw tiled map
        tmr.setView(camera);
        tmr.render();

        //draw entities
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);

        playerView.render(batch, deltaTime);

        for(EntityView entityView : entityViews) {
            entityView.render(batch, deltaTime);
        }
        for(ButtonView button : buttonViews) {
            button.render(batch, deltaTime);
        }

        box2DCamera.setPosition((x * PPM + VIEWPORT_WIDTH / 8) / PPM, (y * PPM + VIEWPORT_HEIGHT / 4) / PPM);
        box2DCamera.update();

        //TODO remove, debugging stuff
        if(Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            debug = !debug;
            Gdx.input.setCursorCatched(!debug);
        }

        //draw box2d world (debug)
        if (debug) {
            debugRenderer.render(world, box2DCamera.combined);
        }
    }

    public void setLevel(Level level) {
        this.level = level;
        this.world = level.getWorld();

        TiledMap tiledMap = level.getTiledMap();
        tmr = new OrthogonalTiledMapRenderer(tiledMap);

        int width = level.getWidth();
        int height = level.getHeight();

        //create main camera for drawing
        camera = new BoundedCamera(0, 0, width, height);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        //create debug camera
        box2DCamera = new BoundedCamera(0, 0, width / PPM, height / PPM);
        box2DCamera.setToOrtho(false, VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);


        //add player view
        playerView = new PlayerView(level.getPlayer());

        //create new entity views
        entityViews.clear();
        buttonViews.clear();
        for(Entity entity : level.getEntities()) {
            addView(entity);
        }
    }

    public void addView(Entity entity) {
        switch (entity.getType()) {
            case BUTTON:
                buttonViews.add(new ButtonView((Button) entity));
                break;
            case BOX:
                entityViews.add(new BoxView((Box) entity));
                break;
            case BULLET:
                System.out.println("No bullet view yet");
                break;
            case EXIT:
                entityViews.add(new ExitView((Exit) entity));
                break;
            case GATE:
                entityViews.add(new GateView((Gate)entity));
                break;
            case PLAYER:
                playerView = new PlayerView((Player)entity);
                System.out.println("WTF");
                break;
            case PORTAL:
                System.out.println("Add portal");
                entityViews.add(new PortalView((Portal)entity));
                break;
        }
    }

    // TODO make separated sets for each entity type
    public void removeView(Entity entity) {
        if(entity.getType().equals(EntityType.BUTTON)) {
            for(ButtonView buttonView : buttonViews) {
                if(buttonView.getModel().equals(entity)) {
                    buttonViews.remove(buttonView);
                    return;
                }
            }
        }
        else {
            for(EntityView entityView : entityViews) {
                if(entityView.getModel().equals(entity)) {
                    entityViews.remove(entityView);
                    return;
                }
            }
        }
    }

    public BoundedCamera getBox2DCamera() {
        return box2DCamera;
    }

}
