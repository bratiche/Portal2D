package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.entities.EntityView;
import com.portal2d.game.view.entities.PlayerView;
import com.portal2d.game.view.weapons.WeaponView;

import java.util.ArrayList;
import java.util.List;

import static com.portal2d.game.view.ViewConstants.*;

/**
 * Visual representation of the {@link PlayState}.
 */
public class PlayScene extends Scene {

    private OrthogonalTiledMapRenderer tmr;

    // Views
    private List<EntityView> entityViews;
    private PlayerView playerView;
    private WeaponView weaponView;

    private BoundedCamera box2DCamera;

    public PlayScene() {
        entityViews = new ArrayList<EntityView>();
    }

    // To draw the cursor
    private final Vector3 tmp = new Vector3();

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        // Draw tiled map
        tmr.setView(camera);
        tmr.render();

        // Draw entities
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(camera.combined);

        playerView.render(batch, deltaTime);

        for(EntityView entityView : entityViews) {
            entityView.render(batch, deltaTime);
        }

        // Draw the cursor
        tmp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(tmp);
        weaponView.render(batch, tmp.x, tmp.y);
    }

    public void setTiledMap(TiledMap tiledMap) {

        tmr = new OrthogonalTiledMapRenderer(tiledMap);

        int tilewidth = (Integer) tiledMap.getProperties().get("tilewidth");
        int mapwidth = (Integer) tiledMap.getProperties().get("width");

        int tileheight = (Integer) tiledMap.getProperties().get("tileheight");
        int mapheight = (Integer) tiledMap.getProperties().get("height");

        int width = tilewidth * mapwidth;
        int height = tileheight * mapheight;

        //create main camera for drawing
        camera = new BoundedCamera(0, 0, width, height);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        //create box2d camera, used for mouse input events
        box2DCamera = new BoundedCamera(0, 0, width / PPM, height / PPM);
        box2DCamera.setToOrtho(false, VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);
    }

    /** Called by the {@link PlayState} whenever an entity is added to the {@link Level}. */
    public void addView(EntityView entityView) {
        entityViews.add(entityView);
    }

    public void addView(PlayerView playerView, WeaponView weaponView) {
        this.playerView = playerView;
        this.weaponView = weaponView;
    }

    public void removeView(EntityView entityView) {
        entityViews.remove(entityView);
    }

    public void clearViews() {
        entityViews.clear();
    }

    public BoundedCamera getBox2DCamera() {
        return box2DCamera;
    }

}
