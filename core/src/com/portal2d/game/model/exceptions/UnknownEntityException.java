package com.portal2d.game.model.exceptions;

import com.portal2d.game.model.entities.EntityType;

/**
 *
 */
public class UnknownEntityException extends RuntimeException {

    public UnknownEntityException() {
        super();
    }

    public UnknownEntityException(String message) {
        super(message);
    }

    public UnknownEntityException(EntityType type) {
        this("Unknown type: " + type);
    }

}