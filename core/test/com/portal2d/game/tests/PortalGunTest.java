package com.portal2d.game.tests;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Surface;
import com.portal2d.game.model.entities.portals.PortableSurface;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import com.portal2d.game.model.weapons.PortalGun;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PortalGunTest {

    private PortalGun portalGun;
    private Entity owner;

    @Before
    public void initialize() {
        Level level = new Level(LevelName.LEVEL_0);

        // Add a non-portable surface
        level.add(new Surface(level, new Vector2(0, 0), 2, 10));
        // Add a portable surface to the right of the non-portable one
        level.add(new PortableSurface(level, new Vector2(10, 0), 2, 10));

        // Add an entity in the middle
        owner = new Entity(level, new Vector2(5, 0), null) {
            public void update() {
            }

            public void beginInteraction(Entity entity) {
            }

            public void endInteraction(Entity entity) {
            }
        };

        level.add(owner);
        portalGun = new PortalGun(level, owner);
    }

    @Test
    public void testPortalsCreation() {
        assertFalse(portalGun.isPortalCreated(PortalColor.BLUE));
        assertFalse(portalGun.isPortalCreated(PortalColor.ORANGE));

        Vector2 position = new Vector2();

        //Shoot blue portal to non-portable surface
        position.set(owner.getPosition().add(-1, 0));
        portalGun.update(position);
        portalGun.actionLeftClick(position);
        assertFalse(portalGun.isPortalCreated(PortalColor.BLUE));

        //Shoot blue portal to portable surface
        position.set(owner.getPosition().add(1, 0));
        portalGun.update(position);
        portalGun.actionLeftClick(position);
        assertTrue(portalGun.isPortalCreated(PortalColor.BLUE));

        //Shoot orange portal to non-portable surface
        position.set(owner.getPosition().add(-1, 0));
        portalGun.update(position);
        portalGun.actionRightClick(position);
        assertFalse(portalGun.isPortalCreated(PortalColor.ORANGE));

        //Shoot orange portal to portable surface
        position.set(owner.getPosition().add(1, 0));
        portalGun.update(position);
        portalGun.actionRightClick(position);
        assertTrue(portalGun.isPortalCreated(PortalColor.ORANGE));
    }

    @Test
    public void testPortalsLinking() {
        assertFalse(portalGun.arePortalsLinked());
        createPortals();
        assertTrue(portalGun.arePortalsLinked());
    }

    @Test
    public void testPortalsDestruction() {
        createPortals();
        portalGun.destroyPortals();
        assertFalse(portalGun.arePortalsLinked());
        assertFalse(portalGun.isPortalCreated(PortalColor.BLUE));
        assertFalse(portalGun.isPortalCreated(PortalColor.ORANGE));
    }

    // Helper method
    private void createPortals() {
        Vector2 position = new Vector2(owner.getPosition().add(1, 0));

        portalGun.update(position);
        portalGun.actionLeftClick(position);

        portalGun.update(position);
        portalGun.actionRightClick(position);
    }

}
