package com.portal2d.game.model.level;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.entities.enemies.Turret;
import com.portal2d.game.model.entities.portals.PortableSurface;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.interactions.GameContactListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Container for all game objects.
 */
public class Level {

    // Level properties
    private LevelName levelName;
    private LevelName nextLevel;
    private boolean finished;

    private World world;

    // Entities
    private Player player;
    private Set<Entity> entities;
    private Set<Projectile> projectiles;

    // Queues
    private Map<Entity, Portal> teleportQueue;
    private Set<Entity> entitiesToRemove;
    private Set<Projectile> projectilesToRemove;

    // To add and remove entities
    private PlayState playState;

    public Level(LevelName levelName, World world, PlayState playState) {
        this.world = world;
        this.levelName = levelName;
        this.playState = playState;

        entities = new HashSet<Entity>();
        projectiles = new HashSet<Projectile>();
        teleportQueue = new HashMap<Entity, Portal>();
        entitiesToRemove = new HashSet<Entity>();
        projectilesToRemove = new HashSet<Projectile>();

        world.setContactListener(GameContactListener.getInstance());
    }

    public void update() {

        // Update player
        player.update();

        // Update all other entities
        for(Entity entity : entities) {
            entity.update();
        }

        for(Projectile projectile : projectiles) {
            projectile.update();
        }

        // Queue processing
        for(Map.Entry<Entity,Portal> entry : teleportQueue.entrySet()) {
            Entity entity = entry.getKey();
            Portal portal = entry.getValue();
            portal.receive(entity);
        }

        removeEntities();

        // Queue clearing
        teleportQueue.clear();
        entitiesToRemove.clear();
        projectilesToRemove.clear();

    }

    public void removeAllEntities() {
        // Destroy all bodies
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        System.out.println(bodies.size);

        for(int i = 0; i < bodies.size; i++) {
            Body body = bodies.get(i);
            world.destroyBody(body);
        }

        // Remove entities
        entities.clear();
        projectiles.clear();
    }

    public void addTeleportQueue(Entity entity, Portal portal){
        teleportQueue.put(entity, portal);
    }

    public void addToRemove(Entity entity) {
        entitiesToRemove.add(entity);
    }

    public void addToRemove(Projectile projectile) {
        projectilesToRemove.add(projectile);
    }

    public void add(Player player) {
        this.player = player;
        playState.add(player);
    }

    public void add(Box box) {
        entities.add(box);
        playState.add(box);
    }

    public void add(Exit exit) {
        entities.add(exit);
        playState.add(exit);
    }

    public void add(Gate gate) {
        entities.add(gate);
        playState.add(gate);
    }

    public void add(Portal portal) {
        entities.add(portal);
        playState.add(portal);
    }

    public void add(Button button) {
        entities.add(button);
        playState.add(button);
    }

    public void add(Bullet bullet) {
        projectiles.add(bullet);
        playState.add(bullet);
    }

    public void add(Turret turret) {
        entities.add(turret);
        playState.add(turret);
    }

    public void add(Acid acid) {
        entities.add(acid);
        playState.add(acid);
    }

    public void add(Surface surface) {
        entities.add(surface);
    }

    public void add(PortableSurface surface) {
        entities.add(surface);
    }

    private void removeEntities() {
        for(Entity entity : entitiesToRemove) {
            playState.remove(entity);
            Body body = entity.getBody();
            world.destroyBody(body);
            entities.remove(entity);
        }
        for(Projectile projectile : projectilesToRemove) {
            playState.remove(projectile);
            Body body = projectile.getBody();
            world.destroyBody(body);
            projectiles.remove(projectile);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public boolean isFinished() {
        return finished;
    }

    public LevelName getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Exit exit) {
        finished = true;
        nextLevel = exit.getDestinyLevel();
    }

    public LevelName getLevelName() {
        return levelName;
    }

}
