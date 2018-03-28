package com.portal2d.game.tests;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.model.weapons.GravityGun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GravityGunTest {

    private GravityGun gravityGun;
    private Entity entityToGrab;

    @Before
    public void initialize() {
        Level level = new Level(LevelName.LEVEL_0);
        Entity owner = new Player(level, new Vector2());
        entityToGrab = new Box(level, new Vector2());
        gravityGun = new GravityGun(level.getWorld(), owner);
    }

    @Test
    public void testGrabAndDrop() {
        assertFalse(gravityGun.hasEntityGrabbed());
        assertTrue(gravityGun.canGrabEntity());
        gravityGun.grabEntity(entityToGrab);
        assertTrue(gravityGun.hasEntityGrabbed());
        assertFalse(gravityGun.canGrabEntity());
        gravityGun.dropEntity();
        assertFalse(gravityGun.hasEntityGrabbed());
        assertTrue(gravityGun.canGrabEntity());
    }

}
