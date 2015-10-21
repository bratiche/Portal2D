package com.portal2d.game.controller.events.events;

import com.portal2d.game.controller.events.listeners.CollisionListener;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Player;

/**
 * Collision between a box and the player
 */
public final class BoxPlayerCollision implements GameEvent<CollisionListener> {

    private Player player;
    private Box box;

    public BoxPlayerCollision(Player player, Box box) {
        this.player = player;
        this.box = box;
    }

    @Override
    public void notify(CollisionListener listener) {
        listener.playerCollidedWithBox(player, box);
    }

}
