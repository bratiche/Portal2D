package com.portal2d.game.model.exceptions;

import com.portal2d.game.model.level.LevelName;

/**
 *
 */
public class NoSuchLevelException extends RuntimeException {

    public NoSuchLevelException() {
        super();
    }

    public NoSuchLevelException(String message) {
        super(message);
    }

    public NoSuchLevelException(LevelName levelName) {
        this(levelName + " does not exist yet.");
    }

}
