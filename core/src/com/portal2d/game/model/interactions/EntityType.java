package com.portal2d.game.model.interactions;

import com.portal2d.game.model.entities.*;

/**
 *
 */
public enum EntityType {

    BOX {
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.interact((Box)e2);
        }
    },
    PLAYER {
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.interact((Player)e2);
        }
    },
    BUTTON {
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.interact((Button)e2);
        }
    },
    EXIT{
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.interact((Exit)e2);
        }
    },
    TILE{
        @Override
        public void interact(Entity e1, Entity e2) {
            e1.interact((Tile)e2);
        }
    };

    public abstract void interact(Entity e1, Entity e2);
}
