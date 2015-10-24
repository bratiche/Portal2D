package com.portal2d.game.model.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.interactions.GameContactListener;
import com.portal2d.game.model.entities.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Container for all game objects.
 */
public class Level {

    public static int levelNumber;
    private LevelName levelName;
    private World world;

    private TiledMap tiledMap;

    //Entities
    private Set<Gate> gates;
    private Set<Button> buttons;
    private Set<Exit> exits;
    private Set<Box> boxes;
    private Set<Tile> tiles;

    //Queues
    private Map<Entity,Portal> teleportQueue;
    //TODO: entity removal system
    private Set<Entity> removalQueue;

    private Player player;

    public Level(World world, TiledMap tiledMap) {
        this.world = world;
        this.tiledMap = tiledMap;
        levelNumber++;

        gates = new HashSet<Gate>();
        buttons = new HashSet<Button>();
        exits = new HashSet<Exit>();
        boxes = new HashSet<Box>();
        tiles = new HashSet<Tile>();

        teleportQueue = new HashMap<Entity, Portal>();
        removalQueue = new HashSet<Entity>();

        world.setContactListener(new GameContactListener());
    }

    public void update() {

        player.update();

        for(Button button : buttons) {
            button.update();
        }

        //Queue processing
        for(Map.Entry<Entity,Portal> e : teleportQueue.entrySet()) {
            e.getValue().recieve(e.getKey());
        }

        //Queue cleaning
        teleportQueue.clear();

    }

    public void addTeleportQueue(Entity e, Portal portal){
        teleportQueue.put(e, portal);
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void add(Box box) {
        boxes.add(box);
    }

    public void add(Button button) {
        buttons.add(button);
    }

    public void add(Exit exit) {
        exits.add(exit);
    }

    public void add(Gate gate) {
        gates.add(gate);
    }

    public int getWidth() {
        int tilewidth = (int) tiledMap.getProperties().get("tilewidth");
        int mapwidth = (int) tiledMap.getProperties().get("width");
        return tilewidth * mapwidth;
    }

    public int getHeight() {
        int tileheight = (int) tiledMap.getProperties().get("tileheight");
        int mapheight = (int) tiledMap.getProperties().get("height");
        return tileheight * mapheight;
    }

}
