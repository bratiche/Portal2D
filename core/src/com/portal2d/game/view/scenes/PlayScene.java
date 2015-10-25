package com.portal2d.game.view.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.controller.states.PlayState;

import static com.portal2d.game.controller.Box2DConstants.PPM;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_HEIGHT;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

/**
 * Visual representation of the {@link PlayState}.
 */
public class PlayScene extends Scene {

    private World world;
    private BoundedCamera camera;
    private Box2DDebugRenderer debugRenderer;

    private Level level;
    private OrthogonalTiledMapRenderer tmr;

    public PlayScene(World world, Level level) {
        this.world = world;
        debugRenderer = new Box2DDebugRenderer();
        setLevel(level);
    }

    @Override
    public void render(SpriteBatch batch) {

        //set camera to follow player
        float x = level.getPlayer().getBody().getPosition().x + VIEWPORT_WIDTH / 8 / PPM;
        float y = level.getPlayer().getBody().getPosition().y + VIEWPORT_HEIGHT / PPM;

        camera.setPosition(x, y);
        camera.update();

        //draw tiled map
        tmr.setView(camera);
        tmr.render();

        //draw box2d world (debug)
        batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world, camera.combined);

    }

    public void setLevel(Level level) {
        this.level = level;
        this.world = level.getWorld();

        TiledMap tiledMap = level.getTiledMap();
        tmr = new OrthogonalTiledMapRenderer(tiledMap);

        int width = level.getWidth();
        int height = level.getHeight();
        camera = new BoundedCamera(0, 0, width / PPM, height / PPM);
        camera.setToOrtho(false, VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);
    }

    public BoundedCamera getCamera() {
        return camera;
    }

}
