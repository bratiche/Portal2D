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

        tiledMap = tmxMapLoader.load("core/assets/levels/test0.tmx");
        tiledMaps.put(LevelName.TEST_LEVEL, tiledMap);

        tiledMap = tmxMapLoader.load("core/assets/levels/test1.tmx");
        tiledMaps.put(LevelName.LEVEL_1, tiledMap);

    }

    public void loadTextures() {
        Texture texture;
        texture = new Texture(Gdx.files.internal("core/assets/backgrounds/menu.png"));
        textures.put(TextureName.MENU_BG, texture);

        texture = new Texture(Gdx.files.internal("core/assets/sprites/cube.png"));
        textures.put(TextureName.SPRITE_BOX, texture);

        texture = new Texture(Gdx.files.internal("core/assets/sprites/gate.png"));
        textures.put(TextureName.ANIM_GATE, texture);
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
