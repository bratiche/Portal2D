package com.portal2d.game.model.level;

/**
 * Enum for all the levels.
 */
public enum LevelName {

    LEVEL_0(false, "TEST"),
    LEVEL_1(true, "MOMENTUM"),
    LEVEL_2(true, "ACID"),
    LEVEL_3(true, "ACID AND TURRETS"),
    LEVEL_4(true, "MAKE THE RIGHT CHOICE"),
    LEVEL_5(true, "ALL"),
    LEVEL_6(true, "GAME OVER");

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
