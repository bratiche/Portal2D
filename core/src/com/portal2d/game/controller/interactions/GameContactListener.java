package com.portal2d.game.controller.interactions;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.portal2d.game.model.entities.Entity;

/**
 * Created by tmatorras on 22/10/15.
 */
public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA().getBody().getUserData()== null || contact.getFixtureB().getBody().getUserData() == null){
            return;
        }

        Entity e1 = (Entity)contact.getFixtureA().getBody().getUserData();
        Entity e2 = (Entity)contact.getFixtureB().getBody().getUserData();


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
