package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.entities.BoxView;

import java.util.HashSet;
import java.util.Set;

import static com.portal2d.game.controller.Box2DConstants.PPM;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_HEIGHT;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

/**
 * Visual representation of the {@link PlayState}.
 */
public class PlayScene extends Scene {

    private Level level;
    private World world;
    private BoundedCamera camera;

    private OrthogonalTiledMapRenderer tmr;
    private Set<BoxView> boxViews;

    //debugging stuff
    private boolean debug = true;
    private Box2DDebugRenderer debugRenderer;
    private BoundedCamera b2dcam;

    public PlayScene(World world, Level level) {
        this.world = world;

        debugRenderer = new Box2DDebugRenderer();

        boxViews = new HashSet<BoxView>();

        setLevel(level);
    }

    @Override
    public void render(SpriteBatch batch) {

        //set camera to follow player
        float x = level.getPlayer().getBody().getPosition().x;
        float y = level.getPlayer().getBody().getPosition().y;

        camera.setPosition(x * PPM + VIEWPORT_WIDTH / 8 , y * PPM + VIEWPORT_HEIGHT);
        camera.update();

        //draw tiled map
        tmr.setView(camera);
        tmr.render();

        //draw entities
        batch.setProjectionMatrix(camera.combined);
        for(BoxView boxView : boxViews) {
            boxView.render(batch);
        }

        //draw box2d world (debug)
        if(debug) {
            b2dcam.setPosition((x * PPM + VIEWPORT_WIDTH / 8) / PPM, (y * PPM + VIEWPORT_HEIGHT) / PPM);
            //b2dcam.setPosition(x / PPM, y / PPM);
            b2dcam.update();
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
        boxViews.clear();
        for(Entity entity : level.getEntities()) {
            if(entity instanceof Box) {
                boxViews.add(new BoxView((Box)entity));
            }
        }
    }

    public BoundedCamera getBox2DCamera() {
        return b2dcam;
    }

}
