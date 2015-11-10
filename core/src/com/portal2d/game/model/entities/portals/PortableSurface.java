package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.Surface;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.level.Level;

/**
 * Special surface able to create portals on it.
 */
public class PortableSurface extends Surface {

    public PortableSurface(Level level, Body body) {
        super(level, body);
        type = EntityType.PORTABLE_SURFACE;
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

}
