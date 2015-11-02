package com.portal2d.game.model.level;

import java.util.NoSuchElementException;
import com.portal2d.game.controller.LevelLoader;
import com.portal2d.game.model.entities.Exit;

/**
 *
 */
public enum LevelName {

    TEST_LEVEL,

    //names will be funnier
    MOMENTUM_TEST,
    LEVEL_2,
    LEVEL_3,
    LEVEL_4,
    LEVEL_5;

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
}
