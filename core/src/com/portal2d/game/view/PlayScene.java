package com.portal2d.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.level.Level;

import static com.portal2d.game.view.ViewConstants.*;
import static com.portal2d.game.controller.Box2DConstants.*;

/**
 * Representacion visual de PlayState
 */
public class PlayScene {

    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    //for the future
    private OrthogonalTiledMapRenderer tmr;

    private Level level;

    public PlayScene(World world, Level level) {
        this.world = world;
        this.level = level;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);
        tmr = new OrthogonalTiledMapRenderer(level.getTiledMap());

        debugRenderer = new Box2DDebugRenderer();
    }

    public void render(SpriteBatch batch) {
        tmr.setView(camera);
        tmr.render();

        batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world, camera.combined);
    }

    public void setLevel(Level level) {
        this.level = level;
        tmr.setMap(level.getTiledMap());
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

}
