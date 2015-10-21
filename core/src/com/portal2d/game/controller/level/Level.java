package com.portal2d.game.controller.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.portal2d.game.controller.events.EventDispatcher;
import com.portal2d.game.controller.events.events.ButtonCollision;
import com.portal2d.game.controller.events.events.BoxPlayerCollision;
import com.portal2d.game.controller.events.listeners.CollisionListener;
import com.portal2d.game.controller.events.listeners.MyCollisionListener;
import com.portal2d.game.model.entities.*;

import java.util.HashSet;
import java.util.Iterator;
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

    private EventDispatcher dispatcher;

    public Level(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        levelNumber++;
        tiles = new HashSet<Tile>();
        buttons = new HashSet<Button>();
        boxes = new HashSet<Box>();

        dispatcher = new EventDispatcher();

        CollisionListener listener = new MyCollisionListener();

        dispatcher.addListener(BoxPlayerCollision.class, listener);
        dispatcher.addListener(ButtonCollision.class, listener);
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

        //Podriamos crear una lista de quien colisiona con quien
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
