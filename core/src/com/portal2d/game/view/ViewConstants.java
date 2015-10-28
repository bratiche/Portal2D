package com.portal2d.game.view;

/**
 *
 */
public interface ViewConstants {

    String TITLE = "Portal 2D";
    int VIEWPORT_WIDTH = 1200;
    int VIEWPORT_HEIGHT = 720;

    enum TextureName {
        ANIM_GATE,
        MENU_BG,
        SPRITE_BOX,
        SPRITE_BUTTON
    }

    //sizes in pixels
    int GATE_WIDTH = 40;
    int GATE_HEIGHT = 180;

    int BUTTON_WIDTH = 130;
    int BUTTON_HEIGHT = 20;

    //animations
    float ANIM_GATE_DELAY =  1 / 12f;

}
