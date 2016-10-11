package com.portal2d.game.tests;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.portal2d.game.model.entities.*;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.entities.portals.PortableSurface;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Test addition and removal of entities
 */
public class LevelTest {

    private Level level;

    @Before
    public void initialize() {
        level = new Level(LevelName.LEVEL_0);
    }

    @Test
    public void testEntityAddition() {
        Switchable switchable = (Switchable) level.add(new Gate(level, new Vector2(), 1));
        level.add(new Button(level, new Vector2(), switchable));
        level.add(new Box(level, new Vector2()));
        level.add(new Surface(level, new Vector2(10, 10), 10, 10));
        level.add(new PortableSurface(level, new Vector2(), 10, 10));

        level.add(new Bullet(level, new Vector2(), new Vector2()));

        Array<Body> bodies = new Array<Body>();
        level.getWorld().getBodies(bodies);

        assertTrue(level.containedEntitiesAmount() == bodies.size);
    }

    @Test(expected = NoSuchElementException.class)
    public void testInvalidEntityRemoval() {
        Box box1 = new Box(level, new Vector2(10, 5));
        Box box2 = new Box(level, new Vector2(15, 5));
        level.add(box1);
        level.addToRemove(box2);

        Bullet bullet1 = new Bullet(level, new Vector2(), new Vector2(1, 0));
        Bullet bullet2 = new Bullet(level, new Vector2(), new Vector2(0, 1));

        level.add(bullet1);
        level.addToRemove(bullet2);
    }

    @Test
    public void testEntityRemoval() {
        level.add(new Player(level, new Vector2(10, 10)));

        Array<Body> bodies = new Array<Body>();

        //Add boxes
        Set<Box> boxes = new HashSet<Box>();
        final int size = 10;

        for(int i = 0; i < size; i++) {
            Box box = (Box) level.add(new Box(level, new Vector2(i * 2, 0)));
            boxes.add(box);
        }

        level.getWorld().getBodies(bodies);
        assertTrue(level.containedEntitiesAmount() == bodies.size);

        //Remove boxes
        for(Box box : boxes) {
            level.addToRemove(box);
        }
        level.update(1 / 60.0f);

        level.getWorld().getBodies(bodies);
        assertTrue(level.containedEntitiesAmount() == bodies.size);

        //Add bullets
        Set<Bullet> bullets = new HashSet<Bullet>();

        for(int i = 0; i < size; i++) {
            Bullet bullet = (Bullet) level.add(new Bullet(level, new Vector2(), new Vector2()));
            bullets.add(bullet);
        }

        level.getWorld().getBodies(bodies);
        assertTrue(level.containedEntitiesAmount() == bodies.size);

        //Remove bullets
        for(Bullet bullet : bullets) {
            level.addToRemove(bullet);
        }
        level.update(1 / 60.0f);

        level.getWorld().getBodies(bodies);
        assertTrue(level.containedEntitiesAmount() == bodies.size);
    }

}
