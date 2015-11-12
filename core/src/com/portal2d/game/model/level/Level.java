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
    private GameContactListener contactListener = GameContactListener.getInstance();

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

        world.setContactListener(contactListener);
    }

    public void update(float dt) {

        // Physics update
        world.step(dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);

        // States/Interactions update
        player.update();

        for(Entity entity : entities) {
            entity.update();
        }

        for(Projectile projectile : projectiles) {
            projectile.update();
        }

        if(!entitiesToRemove.isEmpty() || !projectilesToRemove.isEmpty()) {
            removeEntities();
        }

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
        if(entity == player) {
            player.die();
        }
        else if(!entities.contains(entity)) {
            throw new NoSuchElementException("The entity is not contained in this level " + entity.getType());
        }
        else {
            entitiesToRemove.add(entity);
        }
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
            //if the entity to remove was grabbed by the player, destroy the joint first.
            if(player.getPortalGun().hasEntityGrabbed(entity)) {
                System.out.println(entity.getType() + " DROPPED before removal");
                player.getPortalGun().dropEntity();
            }
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
