package com.portal2d.game.controller.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.controller.events.EventDispatcher;
import com.portal2d.game.controller.events.events.BoxPlayerCollision;
import com.portal2d.game.controller.events.events.ButtonCollision;
import com.portal2d.game.controller.events.listeners.CollisionListener;
import com.portal2d.game.controller.events.listeners.MyCollisionListener;
import com.portal2d.game.model.entities.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Level seria un controller de interacciones entre las entidadades, les mandaria mensajes cuando sus cuerpos
 * collisionan para que interactuen (update: ahora manda eventos a traves del dispatcher).
 */
public class Level implements ContactListener {

    public static int levelNumber;

    TiledMap tiledMap;
    Set<Button> buttons;
    Set<Box> boxes;
    Set<Tile> tiles;
    Player player;

    World world;

    private EventDispatcher dispatcher;

    public Level(World world, TiledMap tiledMap) {
        this.world = world;
        this.tiledMap = tiledMap;
        levelNumber++;
        tiles = new HashSet<Tile>();
        buttons = new HashSet<Button>();
        boxes = new HashSet<Box>();

        dispatcher = new EventDispatcher();

        CollisionListener listener = new MyCollisionListener();

        dispatcher.addListener(BoxPlayerCollision.class, listener);
        dispatcher.addListener(ButtonCollision.class, listener);

        world.setContactListener(this);
    }

    /**
     *
     */
    public void update() {
        //Iterator<Box> boxIterator = boxes.iterator();

        //while(boxIterator.hasNext()) {
        //    Box box = boxIterator.next();
        //    //nada que ver la validacion, es para ver que funcione el dispatcher
        //    if (player.getBody().getLinearVelocity().equals(box.getBody().getLinearVelocity()))
        //        dispatcher.notifyEvent(new BoxPlayerCollision(player, box));
        //}

        //Iterator<Button> buttonIterator = buttons.iterator();

        //while(buttonIterator.hasNext()) {
        //    Button button = buttonIterator.next();
        //    //lo mismo aca, gg
        //    if(player.isJumping())
        //        dispatcher.notifyEvent(new ButtonCollision(button, player));
        //}
    }

    public Player getPlayer() {
        return player;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fA = contact.getFixtureA();
        Fixture fB = contact.getFixtureB();

        if(fA == null || fB == null)
            return;

        Entity e1 = (Entity) fA.getUserData();
        Entity e2 = (Entity) fA.getUserData();

        handle(e1, e2);
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

    private void handle(Entity e1, Entity e2) {

//        if(e2 instanceof Box) {
//            e1.interactWith((Box)e2);
//        }
//        else if(e2 instanceof Button) {
//            e1.interactWith((Button) e2);
//        }

    }

    public World getWorld() {
        return world;
    }
}
