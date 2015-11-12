package com.portal2d.game.model.level;

import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.Entity;

/**
 * Implemented by {@link PlayState}
 */
public interface LevelObserver  {

    public void entityAdded(Entity entity);

    public void entityRemoved(Entity entity);
}
