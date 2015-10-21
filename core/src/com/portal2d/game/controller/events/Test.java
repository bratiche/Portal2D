package com.portal2d.game.controller.events;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.events.events.BoxPlayerCollision;
import com.portal2d.game.controller.events.listeners.CollisionListener;
import com.portal2d.game.controller.events.listeners.MyCollisionListener;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Player;

/**
 * Testerino
 */
public class Test {

    private World world;
    private Player player;
    private Box box;

    EventDispatcher dispatcher;
    public Test() {

        world = new World(new Vector2(0, -9.8f), true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(100, 100));
        player = new Player(world, world.createBody(bodyDef));
        box = new Box(world, world.createBody(bodyDef));

        dispatcher = new EventDispatcher();

        CollisionListener listener = new MyCollisionListener();

        dispatcher.addListener(BoxPlayerCollision.class, listener);

        if (player.getBody().getPosition() == box.getBody().getPosition())
            dispatcher.notifyEvent(new BoxPlayerCollision(player, box));
    }

}
