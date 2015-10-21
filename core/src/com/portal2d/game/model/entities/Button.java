package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 */
public class Button extends StaticEntity {

    //switch
    private boolean pressed;

    public Button(World world, Body body) {
        super(world, body);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void switchPressed() {
        pressed = !pressed;
    }
}
