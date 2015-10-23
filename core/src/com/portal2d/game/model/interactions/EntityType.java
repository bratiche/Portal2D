package com.portal2d.game.model.interactions;

import com.portal2d.game.model.entities.*;

/**
 *
 */
public enum EntityType {

    BOX {
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.beginInteraction((Box) e2);
        }
    },
    PLAYER {
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.beginInteraction((Player) e2);
        }
    },
    BUTTON {
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.beginInteraction((Button) e2);
        }
    },
    EXIT{
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.beginInteraction((Exit) e2);
        }
    },
    TILE{
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.beginInteraction((Tile) e2);
        }
    };

    public abstract void interact(Entity e1, Entity e2);
}
