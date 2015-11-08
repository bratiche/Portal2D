package com.portal2d.game.view.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.model.weapons.Weapon;
import com.portal2d.game.view.ui.CursorView;

/**
 *
 */
public abstract class WeaponView<T extends Weapon> {

    protected T weapon;
    protected CursorView cursor;

    public WeaponView(T weapon) {
        this.weapon = weapon;
    }

    public abstract void render(SpriteBatch batch, float mouseX, float mouseY);
}
