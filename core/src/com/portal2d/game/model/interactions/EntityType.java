package com.portal2d.game.model.interactions;

import static com.portal2d.game.model.ModelConstants.*;

/**
 *
 */
public enum EntityType {

    BOX(BOX_WIDTH, BOX_HEIGHT),
    EXIT(EXIT_WIDTH, EXIT_HEIGHT),
    GATE(GATE_WIDTH, GATE_HEIGHT),
    PORTAL(PORTAL_RADIUS * 2, PORTAL_RADIUS * 2),
    PLAYER(PLAYER_WIDTH, PLAYER_HEIGHT),
    BUTTON(BUTTON_WIDTH, BUTTON_HEIGHT),
    PROJECTILE(PROJECTILE_RADIUS * 2, PROJECTILE_RADIUS * 2),

    //TODO
    SURFACE(0, 0),
    PORTABLE_SURFACE(0, 0);

    private float width;
    private float height;

    EntityType(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}
