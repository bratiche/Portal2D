package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.view.BoundedCamera;
import com.portal2d.game.view.entities.EntityView;
import com.portal2d.game.view.entities.PlayerView;
import com.portal2d.game.view.weapons.WeaponView;

import java.util.HashSet;
import java.util.Set;

import static com.portal2d.game.view.ViewConstants.*;

/**
 * Visual representation of the {@link Level}.
 */
public class PlayScene extends Scene {

    private OrthogonalTiledMapRenderer tmr;

    // Views
    private Set<EntityView> entityViews;
    private PlayerView playerView;
    private WeaponView weaponView;

    private BoundedCamera box2DCamera;

    public PlayScene() {
        entityViews = new HashSet<EntityView>();
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        // Set camera to follow the player
        float x = playerView.getPosition().x;
        float y = playerView.getPosition().y;

        camera.setPosition(x * PPM + VIEWPORT_WIDTH / 8, y * PPM + VIEWPORT_HEIGHT / 4);
        camera.update();

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

        weaponView.render(batch, mouseX, mouseY);

        box2DCamera.setPosition((x * PPM + VIEWPORT_WIDTH / 8) / PPM, (y * PPM + VIEWPORT_HEIGHT / 4) / PPM);
        box2DCamera.update();
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

    /**
     * Called by the {@link PlayState} whenever an entity is added to the level.
     */
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
