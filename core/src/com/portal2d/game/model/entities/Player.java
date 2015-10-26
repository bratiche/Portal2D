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
    public void update(){
        super.update();
        ((PortalGun) weapon).update();
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
