package com.portal2d.game.model.level;

/**
 * Enum for all the levels.
 */
public enum LevelName {

    LEVEL_0(false, "TEST"),
    LEVEL_1(true, "MOMENTUM"),
    LEVEL_2(true, "BOXES AND BUTTONS"),
    LEVEL_3(true, ""),
    LEVEL_4(true, ""),
    LEVEL_5(true, "");

    private boolean locked;
    private String name;

    LevelName(boolean locked, String name) {
        this.locked = locked;
        this.name = name;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getName() {
        return name;
    }

}
