package com.portal2d.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import com.portal2d.game.model.level.LevelName;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that manages game resources (images, tmx files, etc)
 */
public class Assets implements Disposable {

    private Map<Integer, TiledMap> tiledMaps;
    private Map<LevelName, TiledMap> tiledMapsByName;

    public Assets() {
        tiledMaps = new HashMap<Integer, TiledMap>();
        tiledMapsByName = new HashMap<LevelName, TiledMap>();
    }

    public void loadTiledMaps() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap tiledMap;
        int levelNumber = 0;

        tiledMap = tmxMapLoader.load("core/assets/levels/test.tmx");
        tiledMaps.put(levelNumber++, tiledMap);
        tiledMapsByName.put(LevelName.TEST_LEVEL, tiledMap);
    }

    public TiledMap getTiledMap(Integer key) {
        return tiledMaps.get(key);
    }

    public TiledMap getTiledMap(LevelName key) {
        return tiledMapsByName.get(key);
    }

    @Override
    public void dispose() {
        for(TiledMap tiledMap : tiledMaps.values()) {
            tiledMap.dispose();
        }
    }
}
