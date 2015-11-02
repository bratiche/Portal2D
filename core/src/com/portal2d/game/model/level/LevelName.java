package com.portal2d.game.model.level;

import java.io.Serializable;
import java.util.NoSuchElementException;
import com.portal2d.game.controller.LevelLoader;
import com.portal2d.game.model.entities.Exit;

/**
 * TODO: rename
 */
public enum LevelName implements Serializable {

    TEST_LEVEL(false),

    //names will be funnier
    LEVEL_1(true),
    LEVEL_2(true),
    LEVEL_3(true),
    LEVEL_4(true),
    LEVEL_5(true);

    LevelName(boolean locked) {
        this.locked = locked;
    }

    /**
     * This method is used by the {@link LevelLoader} to create {@link Exit}s.
     */
    public static LevelName getLevelName(int levelNumber) {
        for(LevelName levelName : values()) {
            if(levelName.ordinal() == levelNumber)
                return levelName;
        }

        throw new NoSuchElementException();
    }

    private boolean locked;

    public void unlock() {
        this.locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

}
