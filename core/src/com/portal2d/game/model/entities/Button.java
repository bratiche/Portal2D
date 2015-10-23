package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.interactions.EntityType;

/**
 * An entity that sends an order to it's linked Entity whenever it's pressed.
 * TODO: linked Entity
 */
public class Button extends StaticEntity {

    //switch
    private boolean pressed;
    private int interactions;

    public Button(Level level, Body body) {
        super(level, body);
        type = EntityType.BUTTON;
        body.setUserData(this);
    }

    public void update() {
        if(interactions > 0 && !pressed) {
            pressed = true;
            switchLinkedEntityState();
        }
        else if(interactions == 0 && pressed) {
            pressed = false;
            switchLinkedEntityState();
        }
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        interactions++;
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
        interactions--;
    }

    @Override
    public void beginInteraction(Box box) {
        //System.out.println("Boton-Caja");
    }

    @Override
    public void beginInteraction(Player player) {
        //System.out.println("Boton-Player");
    }

    public boolean isPressed() {
        return pressed;
    }

    private void switchLinkedEntityState() {
        System.out.println(pressed);
    }

}
