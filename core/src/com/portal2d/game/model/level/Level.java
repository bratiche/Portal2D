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

    //Level properties
    private LevelName levelName;
    private LevelName nextLevel;
    private boolean finished;

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

    public Level(World world, TiledMap tiledMap, LevelName levelName) {
        this.world = world;
        this.tiledMap = tiledMap;
        this.levelName = levelName;

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
            e.getValue().receive(e.getKey());
        }

        //Queue cleaning
        teleportQueue.clear();

    }

    public void addTeleportQueue(Entity e, Portal portal){
        teleportQueue.put(e, portal);
    }

    public void addToRemove(Entity entity) {
        removalQueue.add(entity);
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public LevelName getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(LevelName nextLevel) {
        this.nextLevel = nextLevel;
    }

    public LevelName getLevelName() {
        return levelName;
    }

}
