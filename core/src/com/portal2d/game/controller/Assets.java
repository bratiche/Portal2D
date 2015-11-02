package com.portal2d.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import com.portal2d.game.model.level.LevelName;

import static com.portal2d.game.view.ViewConstants.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that manages game resources (images, tmx files, etc)
 */
public class Assets implements Disposable {

    //tiled maps
    private Map<LevelName, TiledMap> tiledMaps;

    //textures
    private Map<TextureName, Texture> textures;

    public Assets() {
        tiledMaps = new HashMap<LevelName, TiledMap>();
        textures = new HashMap<TextureName, Texture>();
    }

    public void loadTiledMaps() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap tiledMap;

        tiledMap = tmxMapLoader.load("core/assets/levels/0-test_level.tmx");
        tiledMaps.put(LevelName.TEST_LEVEL, tiledMap);

        tiledMap = tmxMapLoader.load("core/assets/levels/1-momentum_test.tmx");
        tiledMaps.put(LevelName.MOMENTUM_TEST, tiledMap);

        tiledMap = tmxMapLoader.load("core/assets/levels/2-level_2.tmx");
        tiledMaps.put(LevelName.LEVEL_2, tiledMap);

    }

    public void loadTextures() {
        Texture texture;
        texture = new Texture(Gdx.files.internal("core/assets/backgrounds/menu.png"));
        textures.put(TextureName.MENU_BG, texture);

        texture = new Texture(Gdx.files.internal("core/assets/sprites/box1.png"));
        textures.put(TextureName.SPRITE_BOX, texture);

        texture = new Texture(Gdx.files.internal("core/assets/sprites/gate.png"));
        textures.put(TextureName.ANIM_GATE, texture);

        texture = new Texture(Gdx.files.internal("core/assets/sprites/button.png"));
        textures.put(TextureName.SPRITE_BUTTON, texture);

        texture = new Texture(Gdx.files.internal("core/assets/sprites/player.png"));
        textures.put(TextureName.ANIM_PLAYER, texture);

        texture = new Texture(Gdx.files.internal("core/assets/sprites/exit.png"));
        textures.put(TextureName.SPRITE_EXIT, texture);
    }

    public TiledMap getTiledMap(LevelName key) {

        TiledMap tiledMap = tiledMaps.get(key);

        if(tiledMap == null) {
            throw new NoSuchLevelException(key + " does not exist yet.");
        }

        return tiledMap;
    }

    public Texture getTexture(TextureName key) {
        return textures.get(key);
    }

    @Override
    public void dispose() {
        for(TiledMap tiledMap : tiledMaps.values()) {
            tiledMap.dispose();
        }

        for(Texture texture : textures.values()) {
            texture.dispose();
        }
    }
}
