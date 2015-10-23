package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.interactions.EntityType;

/**
 * An entity that sends an order to it's linked Entity whenever it's pressed.
 * TODO: linked Entity
 */
public class Button extends StaticEntity {

    //switch
    private boolean pressed;

    public Button(World world, Body body) {
        super(world, body);
        type = EntityType.BUTTON;
        body.setUserData(this);
    }

    @Override
    public void interact(Box box) {
        System.out.println("Boton-Caja");
    }

    @Override
    public void interact(Player player) {
        System.out.println("Boton-Player");
    }

    @Override
    public void interact(Exit exit) {

    }

    @Override
    public void interact(Button button) {

    }

    @Override
    public void interact(Tile tile) {

    }

    public boolean isPressed() {
        return pressed;
    }

    public void switchPressed() {
        pressed = !pressed;
    }
}
