package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Button;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Gate;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.entities.BoxView;
import com.portal2d.game.view.entities.ButtonView;
import com.portal2d.game.view.entities.EntityView;
import com.portal2d.game.view.entities.GateView;

import java.util.HashSet;
import java.util.Set;

import static com.portal2d.game.controller.Box2DConstants.PPM;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_HEIGHT;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

/**
 * Visual representation of the {@link Level}.
 */
public class PlayScene extends Scene {

    private Level level;

    private BoundedCamera camera;
    private OrthogonalTiledMapRenderer tmr;

    private Set<EntityView> entityViews;

    // Separate for for drawing Buttons, this is to make sure the buttons are drawn on top of the other entities
    private Set<ButtonView> buttons;

    //debugging stuff
    private boolean debug = true;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private BoundedCamera b2dcam;

    public PlayScene(World world, Level level) {
        this.world = world;

        debugRenderer = new Box2DDebugRenderer();

        entityViews = new HashSet<EntityView>();

        buttons = new HashSet<ButtonView>();

        setLevel(level);
    }

    @Override
    public void render(SpriteBatch batch) {

        //set camera to follow player
        float x = level.getPlayer().getBody().getPosition().x;
        float y = level.getPlayer().getBody().getPosition().y;

        camera.setPosition(x * PPM + VIEWPORT_WIDTH / 8, y * PPM + VIEWPORT_HEIGHT);
        camera.update();

        //draw tiled map
        tmr.setView(camera);
        tmr.render();

        //draw entities
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);

        for(EntityView entityView : entityViews) {
            entityView.render(batch, deltaTime);
        }
        for(ButtonView button : buttons) {
            button.render(batch, deltaTime);
        }

        b2dcam.setPosition((x * PPM + VIEWPORT_WIDTH / 8) / PPM, (y * PPM + VIEWPORT_HEIGHT) / PPM);
        b2dcam.update();

        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            debug = !debug;
        }

        //draw box2d world (debug)
        if (debug) {
            debugRenderer.render(world, b2dcam.combined);
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
        b2dcam = new BoundedCamera(0, 0, width / PPM, height / PPM);
        b2dcam.setToOrtho(false, VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);

        //create entity views
        entityViews.clear();
        buttons.clear();

        for(Entity entity : level.getEntities()) {
            if(entity.getType().equals(EntityType.BOX)) {
                entityViews.add(new BoxView((Box) entity));
            }
            else if(entity.getType().equals(EntityType.GATE)) {
                entityViews.add(new GateView((Gate)entity));
            }
            else if(entity.getType().equals(EntityType.BUTTON)) {
                buttons.add(new ButtonView((Button)entity));
            }
            //TODO: add other types
        }
    }

    public BoundedCamera getBox2DCamera() {
        return b2dcam;
    }

}
