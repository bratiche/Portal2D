package com.portal2d.game.controller.events.listeners;

import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Button;
import com.portal2d.game.model.entities.DynamicEntity;
import com.portal2d.game.model.entities.Player;

/**
 * Un ejemplo de CollisionListener, podriamos tener un listener para cada entidad, o uno para todos y todos para uno (?
 */
public class MyCollisionListener implements CollisionListener {

    @Override
    public void playerCollidedWithBox(Player player, Box box) {
        //player.canGrab(box);
        System.out.println("se chocaron a la caja");
    }

    @Override
    public void entityCollidedWithButton(DynamicEntity player, Button button) {
        button.switchPressed();
        System.out.println("Se chocaron al boton");
    }

    //other events
}
