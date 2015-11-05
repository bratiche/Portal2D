package com.portal2d.game.model.level;

import java.util.NoSuchElementException;

/**
 *
 */
public enum LevelName {

    TEST_LEVEL(false),
    MOMENTUM_TEST(true),

    //names will be funnier
    LEVEL_2(true),
    LEVEL_3(true),
    LEVEL_4(true),
    LEVEL_5(true);

    private boolean locked;

    LevelName(boolean locked) {
        this.locked = locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    /**
     * @deprecated
     */
    public static LevelName getLevelName(int levelNumber) {
        for(LevelName levelName : values()) {
            if(levelName.ordinal() == levelNumber)
                return levelName;
        }

        throw new NoSuchElementException();
    }

}
