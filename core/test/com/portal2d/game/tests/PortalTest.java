package com.portal2d.game.tests;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PortalTest {

    private Portal portal1;
    private Portal portal2;
    private Level level;

    @Before
    public void initialize() {
        level = new Level(LevelName.LEVEL_0);

        portal1 = (Portal) level.add(new Portal(level,new Vector2(0,0), PortalColor.BLUE, new Vector2(1,0)));
        portal2 = (Portal) level.add(new Portal(level,new Vector2(1,1), PortalColor.ORANGE, new Vector2(0,1)));

        portal1.setOppositePortal(portal2);
        portal2.setOppositePortal(portal1);
    }

    @Test
    public void testTeleport() {
        Entity entity = new Box(level, new Vector2(100,100));

        Vector2 prevPosition = new Vector2(entity.getPosition());

       // assertTrue(entity.getPosition().equals(new Vector2(100,100)));
        portal1.beginInteraction(entity);
        portal1.update();

        // The entity has changed position
        assertFalse(entity.getPosition().equals(prevPosition));
    }

    @Test
    public void testCleaningList() {
        Entity entity = new Box(level, new Vector2(100,100));

        portal1.beginInteraction(entity);
        assertTrue(portal1.hasEntitiesToSend());
        portal1.update();
        assertFalse(portal1.hasEntitiesToSend());
    }

}
