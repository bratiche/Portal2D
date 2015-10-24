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
    private Map<Integer, TiledMap> tiledMaps;
    private Map<LevelName, TiledMap> tiledMapsByName;

    //textures
    private Map<TextureName, Texture> textures;

    public Assets() {
        tiledMaps = new HashMap<Integer, TiledMap>();
        tiledMapsByName = new HashMap<LevelName, TiledMap>();
        textures = new HashMap<TextureName, Texture>();
    }

    public void loadTiledMaps() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap tiledMap;
        int levelNumber = 0;

        tiledMap = tmxMapLoader.load("core/assets/levels/test.tmx");
        tiledMaps.put(levelNumber++, tiledMap);
        tiledMapsByName.put(LevelName.TEST_LEVEL, tiledMap);
    }

    public void loadTextures() {
        Texture texture;
        texture = new Texture(Gdx.files.internal("core/assets/backgrounds/menu.png"));
        textures.put(TextureName.MENU_BG, texture);
    }

    public TiledMap getTiledMap(Integer key) {
        return tiledMaps.get(key);
    }

    public TiledMap getTiledMap(LevelName key) {
        return tiledMapsByName.get(key);
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
