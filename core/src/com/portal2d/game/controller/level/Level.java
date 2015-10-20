package com.portal2d.game.controller.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Button;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.Tile;

import java.util.HashSet;
import java.util.Set;

/**
 * Level seria un controller de interacciones entre las entidadades, les mandaria mensajes cuando sus cuerpos
 * collisionan para que interactuen.
 */
public class Level {

    public static int levelNumber;

    TiledMap tiledMap;
    Player player;
    Set<Button> buttons;
    Set<Box> boxes;
    Set<Tile> tiles;

    public Level(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        levelNumber++;
        tiles = new HashSet<Tile>();
        buttons = new HashSet<Button>();
        boxes = new HashSet<Box>();
    }

    public void update() {

    }

    public Player getPlayer() {
        return player;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

}
