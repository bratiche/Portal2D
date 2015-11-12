package com.portal2d.game.model.level;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.ModelConstants;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Exit;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.Projectile;
import com.portal2d.game.model.interactions.GameContactListener;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.portal2d.game.model.ModelConstants.Box2D.POSITION_ITERATIONS;
import static com.portal2d.game.model.ModelConstants.Box2D.VELOCITY_ITERATIONS;

/**
 * Container for all game objects.
 */
public class Level extends Observable {

    private World world;

    // Level properties
    private LevelName levelName;
    private LevelName nextLevel;
    private boolean finished;

    // Entities
    private Player player;
    private Set<Entity> entities;
    private Set<Projectile> projectiles;

    private Set<Entity> entitiesToRemove;
    private Set<Projectile> projectilesToRemove;

    public Level(LevelName levelName, LevelObserver...observers) {
        super(observers);
        this.levelName = levelName;

        entities = new HashSet<Entity>();
        projectiles = new HashSet<Projectile>();
        entitiesToRemove = new HashSet<Entity>();
        projectilesToRemove = new HashSet<Projectile>();

        world = new World(ModelConstants.Box2D.DEFAULT_GRAVITY, true);

        world.setContactListener(GameContactListener.getInstance());
    }

    public void update(float dt) {

        // Physics update
        world.step(dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

        // States/Interactions update

        //Update player
        player.update();

        // Update all other entities
        for(Entity entity : entities) {
            entity.update();
        }

        for(Projectile projectile : projectiles) {
            projectile.update();
        }

        if(!entitiesToRemove.isEmpty() || !projectilesToRemove.isEmpty()) {
            removeEntities();
        }

        // Queue clearing
        entitiesToRemove.clear();
        projectilesToRemove.clear();

    }

    public void removeAllEntities() {
        // Remove all entities
        entitiesToRemove.addAll(entities);
        entities.clear();

        projectilesToRemove.addAll(projectiles);
        projectiles.clear();

        removeEntities();
    }

    public void addToRemove(Entity entity) {
        if(!entities.contains(entity)) {
            throw new NoSuchElementException("The entity is not contained in this level");
        }
        entitiesToRemove.add(entity);
    }

    public void addToRemove(Projectile projectile) {
        if(!projectiles.contains(projectile)) {
            throw new NoSuchElementException("The entity is not contained in this level");
        }
        projectilesToRemove.add(projectile);
    }

    public Entity add(Entity entity) {
        entities.add(entity);
        notifyObservers(entity, true);
        return entity;
    }

    public Projectile add(Projectile projectile) {
        projectiles.add(projectile);
        notifyObservers(projectile, true);
        return projectile;
    }

    public Player add(Player player) {
        this.player = player;
        notifyObservers(player, true);
        return player;
    }

    private void removeEntities() {
        for(Entity entity : entitiesToRemove) {
            Body body = entity.getBody();
            world.destroyBody(body);
            entities.remove(entity);
            notifyObservers(entity, false);
        }
        entitiesToRemove.clear();
        for(Projectile projectile : projectilesToRemove) {
            Body body = projectile.getBody();
            world.destroyBody(body);
            projectiles.remove(projectile);
            notifyObservers(projectile, false);
        }
        projectilesToRemove.clear();
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

    /** Returns the TOTAL amount of entities that are contained in this level */
    public int containedEntitiesAmount() {
        return entities.size() + projectiles.size() + (player == null ? 0 : 1);
    }

}
