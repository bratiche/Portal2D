package com.portal2d.game.model.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.interactions.GameContactListener;

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

    // Projectiles (entities that are created at runtime)
    private Set<Projectile> projectiles;

    //Queues
    private Map<Entity,Portal> teleportQueue;
    private Set<Entity> removalQueue;

    //Player
    private Player player;

    public Level(World world, TiledMap tiledMap, LevelName levelName) {
        this.world = world;
        this.tiledMap = tiledMap;
        this.levelName = levelName;

        entities = new HashSet<Entity>();
        projectiles = new HashSet<Projectile>();
        teleportQueue = new HashMap<Entity, Portal>();
        removalQueue = new HashSet<Entity>();

        world.setContactListener(new GameContactListener());
    }

    public void update() {

        //Update player
        player.update();

        //Update all other entities
        for(Entity entity : entities) {
            entity.update();
        }

        for(Projectile projectile : projectiles) {
            projectile.update();
        }

        //Queue processing
        for(Map.Entry<Entity,Portal> entry : teleportQueue.entrySet()) {
            entry.getValue().receive(entry.getKey());
        }

        //Remove entities
        if(!removalQueue.isEmpty())
            removeEntities();

        //Queue cleaning
        teleportQueue.clear();
        removalQueue.clear();
    }

    public void addTeleportQueue(Entity e, Portal portal){
        teleportQueue.put(e, portal);
    }

    public void addToRemove(Entity projectile) {
        removalQueue.add(projectile);
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

    public void add(Projectile projectile) {
        projectiles.add(projectile);
    }

    private void removeEntities() {
        for(Entity entity : removalQueue) {
            Body body = entity.getBody();
            world.destroyBody(body);
            if(entity.getType().equals(EntityType.PROJECTILE)) {
                projectiles.remove(entity);
            }
            else {
                entities.remove(entity);
            }
        }
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
