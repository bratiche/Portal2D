package com.portal2d.game.model.level;

import com.portal2d.game.model.entities.Entity;

/**
 *
 */
public interface LevelObserver  {

    public void entityAdded(Entity entity);

    public void entityRemoved(Entity entity);
}
