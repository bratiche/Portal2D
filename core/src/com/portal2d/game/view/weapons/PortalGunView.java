package com.portal2d.game.view.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.view.ViewConstants;
import com.portal2d.game.view.ui.CursorView;

/**
 *
 */
public class PortalGunView extends WeaponView<PortalGun> {

    private TextureRegion[][] regions;

    public PortalGunView(PortalGun weapon) {
        super(weapon);

        Texture texture = Portal2D.assets.getTexture(ViewConstants.TextureName.GAME_CURSOR);
        regions = Sprite.split(texture, texture.getWidth() / 4, texture.getHeight());
        cursor = new CursorView(regions[0][0]);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //shoot blue portal anim
        }
        else if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            //shoot orange portal anim
        }

        if(weapon.arePortalsLinked()) {
            cursor.setTexture(regions[0][3]);
        }
        else if(weapon.isPortalCreated(PortalColor.BLUE)) {
            cursor.setTexture(regions[0][1]);
        }
        else if(weapon.isPortalCreated(PortalColor.ORANGE)) {
            cursor.setTexture(regions[0][2]);
        }
        else {
            cursor.setTexture(regions[0][0]);
        }

        cursor.render(batch, mouseX, mouseY);
    }
}
