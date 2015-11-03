package com.portal2d.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
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

    //fonts
    private Map<FontName, BitmapFont> fonts;

    public Assets() {
        tiledMaps = new HashMap<LevelName, TiledMap>();
        textures = new HashMap<TextureName, Texture>();
        fonts = new HashMap<FontName, BitmapFont>();
    }

    public void loadTiledMaps() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TiledMap tiledMap;

        tiledMap = tmxMapLoader.load("core/assets/levels/test0.tmx");
        tiledMaps.put(LevelName.TEST_LEVEL, tiledMap);

        tiledMap = tmxMapLoader.load("core/assets/levels/test1.tmx");
        tiledMaps.put(LevelName.TEST_LEVEL_2, tiledMap);
    }

    public void loadTextures() {

        textures.put(TextureName.MENU_BG, new Texture(Gdx.files.internal("core/assets/backgrounds/menu.png")));

        textures.put(TextureName.SPRITE_BOX, new Texture(Gdx.files.internal("core/assets/sprites/box.png")));

        textures.put(TextureName.ANIM_GATE, new Texture(Gdx.files.internal("core/assets/sprites/gate.png")));

        textures.put(TextureName.SPRITE_BUTTON, new Texture(Gdx.files.internal("core/assets/sprites/button.png")));

        textures.put(TextureName.ANIM_PLAYER, new Texture(Gdx.files.internal("core/assets/sprites/player.png")));

        textures.put(TextureName.BG1, new Texture(Gdx.files.internal("core/assets/backgrounds/bg1.jpg")));

    }

    public void createFonts() {
        fonts.put(FontName.PORTAL, createFont("core/assets/fonts/Portal.ttf", 60));
        fonts.put(FontName.PORTAL_33, createFont("core/assets/fonts/Portal.ttf", 33));

        fonts.put(FontName.DIN, createFont("core/assets/fonts/DINM.ttf", 60));
        fonts.put(FontName.DIN_33, createFont("core/assets/fonts/DINM.ttf", 33));

        fonts.put(FontName.DINB, createFont("core/assets/fonts/DINB.ttf", 60));
        fonts.put(FontName.DINB_33, createFont("core/assets/fonts/DINB.ttf", 33));

        fonts.put(FontName.MOLOT, createFont("core/assets/fonts/Molot.otf", 60));
        fonts.put(FontName.MOLOT_33, createFont("core/assets/fonts/Molot.otf", 33));
    }

    private BitmapFont createFont(String path, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        return font;
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

    public BitmapFont getFont(FontName key) {
        return fonts.get(key);
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
