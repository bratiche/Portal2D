package com.portal2d.game.controller.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.interactions.GameContactListener;
import com.portal2d.game.model.entities.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Container for all game objects.
 */
public class Level {

    public static int levelNumber;

    World world;

    TiledMap tiledMap;
    Set<Button> buttons;
    Set<Exit> exits;
    Set<Box> boxes;

    Set<Tile> tiles;

    Player player;

    public Level(World world, TiledMap tiledMap) {
        this.world = world;
        this.tiledMap = tiledMap;
        levelNumber++;
        tiles = new HashSet<Tile>();
        buttons = new HashSet<Button>();
        boxes = new HashSet<Box>();
        exits = new HashSet<Exit>();

        world.setContactListener(new GameContactListener());
    }

    public void update() {

    }

    public Player getPlayer() {
        return player;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public World getWorld() {
        return world;
    }
}
