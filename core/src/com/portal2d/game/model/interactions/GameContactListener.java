package com.portal2d.game.model.interactions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.entities.portals.PortalProjectile;

/**
 *
 */
public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        Vector2 normal = contact.getWorldManifold().getNormal();

        if(bodyA.getUserData() == null || bodyB.getUserData() == null){
            return;
        }

        Entity e1 = (Entity)bodyA.getUserData();
        Entity e2 = (Entity)bodyB.getUserData();

        if(e1.getType() == EntityType.PORTAL_PROJECTILE && e2.getType() == EntityType.PORTABLE_SURFACE) {
            PortalProjectile projectile = (PortalProjectile)e1;
            System.out.println("Contact normal:" + normal.angle());
            projectile.setPortalNormal(normal);

        }
        else if(e2.getType() == EntityType.PORTAL_PROJECTILE && e1.getType() == EntityType.PORTABLE_SURFACE){
            PortalProjectile projectile = (PortalProjectile)e2;
            System.out.println("Contact normal:" + normal.angle());
            projectile.setPortalNormal(normal);

        }

//        System.out.println(contact.getWorldManifold().getNormal().angle());

        e1.beginInteraction(e2);
        e2.beginInteraction(e1);
    }

    @Override
    public void endContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        if(bodyA.getUserData()== null || bodyB.getUserData() == null){
            return;
        }

        Entity e1 = (Entity)bodyA.getUserData();
        Entity e2 = (Entity)bodyB.getUserData();

        e1.endInteraction(e2);
        e2.endInteraction(e1);
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
