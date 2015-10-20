package com.portal2d.game.controller;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Disposable;
import com.portal2d.game.controller.level.LevelName;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja los recursos del juego (imagenes, archivos tmx).
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

        tiledMap = tmxMapLoader.load("core/assets/levels/test.tmx");
        tiledMaps.put(1, tiledMap);
        tiledMapsByName.put(LevelName.TEST_LEVEL, tiledMap);
    }

    public TiledMap getTiledMap(Integer key) {
        return tiledMaps.get(key + 1);
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
