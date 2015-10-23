package com.portal2d.game.model.interactions;

import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;

/**
 *
 */
public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData()== null || bodyB.getUserData() == null){
            return;
        }

        Entity e1 = (Entity)bodyA.getUserData();
        Entity e2 = (Entity)bodyB.getUserData();

        e1.interact(e2);
        e2.interact(e1);
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
