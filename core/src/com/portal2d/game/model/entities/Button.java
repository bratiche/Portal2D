package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 */
public class Button extends StaticEntity {

    //switch
    private boolean pressed;

    public Button(Body body) {
        super(body);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void switchPressed() {
        pressed = !pressed;
    }
}
