package com.portal2d.game.model.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.interactions.EntityType;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.model.weapons.Weapon;

/**
 * The main entity in the game, the user controls this entity.
 */
public class Player extends DynamicEntity {

    private Weapon weapon;

    public Player(Level level, Body body) {
        super(level, body);
        type = EntityType.PLAYER;
        body.setUserData(this);
        weapon = new PortalGun(level, this);
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    @Override
    public void beginInteraction(Box box) {
    }

    @Override
    public void beginInteraction(Player player) {

    }

    @Override
    public void beginInteraction(Exit exit) {

    }

    @Override
    public void beginInteraction(Button button) {
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void update(){
        ((PortalGun) weapon).update();
        //System.out.println("Player velocity: " + body.getLinearVelocity());
    }

}
