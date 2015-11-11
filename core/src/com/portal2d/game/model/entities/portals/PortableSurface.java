package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.EntityType;
import com.portal2d.game.model.entities.Surface;
import com.portal2d.game.model.level.Level;

/**
 * Special surface able to create portals on it.
 */
public class PortableSurface extends Surface {

    public PortableSurface(Level level, Vector2 position, float width, float height) {
        super(level, position, width, height);
        type = EntityType.PORTABLE_SURFACE; //Overrides superclass type
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
