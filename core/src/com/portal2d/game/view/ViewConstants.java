package com.portal2d.game.view;

import com.badlogic.gdx.graphics.Color;

/**
 *
 */
public interface ViewConstants {

    String TITLE = "Portal 2D";
    int VIEWPORT_WIDTH = 1200;
    int VIEWPORT_HEIGHT = 720;
    float PPM = 100.0f; // scale for box2d world, pixels per meter

    enum TextureName {
        PORTAL2D_LOGO,
        MENU_BG,
        SPRITE_BOX,
        SPRITE_BUTTON,
        SPRITE_EXIT,
        SPRITE_TURRET,
        SPRITE_BULLET,
        ANIM_GATE,
        ANIM_PLAYER,
        BLUE_PORTAL,
        ORANGE_PORTAL,
        GAME_CURSOR
    }

    enum FontName {
        PORTAL,
        PORTAL_33,
        DINM,
        DINM_33,
        DINB,
        DINB_33,
        MOLOT,
        MOLOT_33
    }

    /* Sprite sizes in pixels */
    int GATE_WIDTH = 40;
    int GATE_HEIGHT = 180;

    int BUTTON_WIDTH = 120;
    int BUTTON_HEIGHT = 22;

    int BOX_WIDTH = 60;
    int BOX_HEIGHT = 60;

    int PLAYER_WIDTH = 120;
    int PLAYER_HEIGHT= 120;

    int EXIT_WIDTH = 30;
    int EXIT_HEIGHT = 180;

    int PORTAL_WIDTH = 120;
    int PORTAL_HEIGHT = 20;

    /* Animations */
    float ANIM_GATE_DELAY =  1 / 12f;
    float ANIM_PLAYER_DELAY = 1 / 12f;

    /* Menus */
    int SPACE_BETWEEN_BUTTONS = 125;

    int BACK_BUTTON_X = 100;
    int BACK_BUTTON_Y = 100;

//    String INSTRUCTIONS_TEXT = "Welcome to POrtal2D!\n\nThis game is a puzzle style game\nwhere you will have " +
//                                "to find your\nway through the map using portals.\nOnly two portals can be active\n" +
//                                "simultaneously, you can exit and\nentry in both.\nBoxes can be used to activate gates\n" +
//                                "(both in color RED).\nUse W,A,S,D to move, right and left \nclick to create portals, " +
//                                "P to pause.\nPortals only can be created \non portable surfaces, distinguished\n" +
//                                "by color WHITE.\nENJOY!";

    // Left click is also used to throw away objects that are grabbed
    String INSTRUCTIONS_TEXT = "Controls:\n\n A - Move left\n D - Move right\n W - Jump\n F - Grab/Drop objects\n\n " +
                               "Left Click - Shoot blue POrtal\n Right Click - Shoot orange POrtal\n X - Destroy POrtals" +
                               "\n\n P - Pause game\n R - Restart level";

    // Colors in rgba format
    Color BLUE_PORTAL_COLOR = new Color(0x1488C7ff);
    Color ORANGE_PORTAL_COLOR = new Color(0x9C7C29ff);

}
