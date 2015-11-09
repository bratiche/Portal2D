package com.portal2d.game.view.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.model.weapons.PortalGun;
import com.portal2d.game.view.ViewConstants.TextureName;

/**
 *
 */
public class PortalGunView extends WeaponView<PortalGun> {

    private TextureRegion[][] regions;
    private int width;
    private int height;
    public PortalGunView(PortalGun weapon) {
        super(weapon);

        Texture texture = Portal2D.assets.getTexture(TextureName.GAME_CURSOR);
        regions = Sprite.split(texture, texture.getWidth() / 4, texture.getHeight());
        width = regions[0][0].getRegionWidth();
        height = regions[0][0].getRegionHeight();

        Pixmap pixmap = new Pixmap(Gdx.files.internal("core/assets/sprites/empty-cursor.png"));
        Cursor empty = Gdx.graphics.newCursor(pixmap, width / 2, height / 2);
        pixmap.dispose();
        Gdx.graphics.setCursor(empty);
    }

    @Override
    public void render(SpriteBatch batch, float mouseX, float mouseY) {

        TextureRegion region = regions[0][0];

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            //shoot blue portal anim
        }
        else if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            //shoot orange portal anim
        }

        if(weapon.arePortalsLinked()) {
            region = regions[0][3];
        }
        else if(weapon.isPortalCreated(PortalColor.BLUE)) {
            region = regions[0][1];
        }
        else if(weapon.isPortalCreated(PortalColor.ORANGE)) {
            region = regions[0][2];
        }

        batch.begin();
        batch.draw(region, mouseX - width / 2, mouseY - height / 2);
        batch.end();

    }
}
