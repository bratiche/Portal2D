package com.portal2d.game.controller.events.listeners;

import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Button;
import com.portal2d.game.model.entities.DynamicEntity;
import com.portal2d.game.model.entities.Player;

/**
 *
 */
public interface CollisionListener extends EventListener {

    public void playerCollidedWithBox(Player player, Box box);

    public void entityCollidedWithButton(DynamicEntity player, Button button);

    //others...
}
