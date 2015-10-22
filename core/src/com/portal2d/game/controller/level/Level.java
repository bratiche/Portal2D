package com.portal2d.game.controller.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.interactions.GameContactListener;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Button;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.Tile;

import java.util.HashSet;
import java.util.Set;

/**
 * Level seria un controller de interacciones entre las entidadades, les mandaria mensajes cuando sus cuerpos
 * collisionan para que interactuen (update: ahora manda eventos a traves del dispatcher).
 */
public class Level {

    public static int levelNumber;

    TiledMap tiledMap;
    Set<Button> buttons;
    Set<Box> boxes;
    Set<Tile> tiles;
    Player player;

    World world;

    public Level(World world, TiledMap tiledMap) {
        this.world = world;
        this.tiledMap = tiledMap;
        levelNumber++;
        tiles = new HashSet<Tile>();
        buttons = new HashSet<Button>();
        boxes = new HashSet<Box>();

        world.setContactListener(new GameContactListener());
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


    public World getWorld() {
        return world;
    }
}
