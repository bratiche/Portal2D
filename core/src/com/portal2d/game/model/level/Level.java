package com.portal2d.game.model.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.interactions.Definitions;
import com.portal2d.game.model.interactions.GameContactListener;
import com.portal2d.game.model.entities.*;
import com.sun.org.apache.xpath.internal.SourceTree;

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
    private Set<Entity> entities;

    //Queues
    private Map<Entity,Portal> teleportQueue;
    private Set<Entity> removalQueue;
    private Map<Entity,Vector2> moveQueue;
    private Map<Portal,Definitions> creationQueue;

    //Player
    private Player player;

    public Level(World world, TiledMap tiledMap, LevelName levelName) {
        this.world = world;
        this.tiledMap = tiledMap;
        this.levelName = levelName;

        entities = new HashSet<Entity>();

        teleportQueue = new HashMap<Entity, Portal>();
        removalQueue = new HashSet<Entity>();
        moveQueue = new HashMap<Entity,Vector2>();
        creationQueue = new HashMap<Portal,Definitions>();

        world.setContactListener(new GameContactListener());
    }

    public void update() {

        //Update player
        player.update();

        //Update all other entities
        for(Entity entity : entities) {
            entity.update();
        }

        //Remove entities
        if(!removalQueue.isEmpty())
            removeEntities();

        //Queue processing
        for(Map.Entry<Entity,Portal> entry : teleportQueue.entrySet()) {
            entry.getValue().receive(entry.getKey());
        }

        for(Map.Entry<Entity,Vector2> entry : moveQueue.entrySet()) {
            entry.getKey().getBody().setTransform(entry.getValue(),0);
        }

        for(Map.Entry<Portal,Definitions> entry : creationQueue.entrySet()) {
            BodyDef bdef = entry.getValue().getBdef();
            FixtureDef fdef = entry.getValue().getFdef();
            Body body = world.createBody(bdef);
            body.createFixture(fdef);
            body.setUserData(entry.getKey());
            entry.getKey().setBody(body);
            entry.getValue().disposeShape();
        }

        //Queue cleaning
        teleportQueue.clear();
        moveQueue.clear();
        creationQueue.clear();

    }

    public void addTeleportQueue(Entity e, Portal portal){
        teleportQueue.put(e, portal);
    }

    public void addToRemove(Entity entity) {
        removalQueue.add(entity);
    }

    public void addToMoveQueue(Entity e, Vector2 position){
        moveQueue.put(e, position);
    }

    public void addToCreationQueue(Portal portal ,Definitions definitions) {
        creationQueue.put(portal,definitions);
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

    public void addPlayer(Player player) {
        this.player = player;
    }

    public void add(Entity entity) {
        entities.add(entity);
    }

    private void removeEntities() {

        for(Entity entity : removalQueue) {
            Body body = entity.getBody();
            world.destroyBody(body);
            entities.remove(entity);
        }
        removalQueue.clear();
    }

    public int getWidth() {
        int tilewidth = (Integer) tiledMap.getProperties().get("tilewidth");
        int mapwidth = (Integer) tiledMap.getProperties().get("width");
        return tilewidth * mapwidth;
    }

    public int getHeight() {
        int tileheight = (Integer) tiledMap.getProperties().get("tileheight");
        int mapheight = (Integer) tiledMap.getProperties().get("height");
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

    public Set<Entity> getEntities() {
        return entities;
    }

}
