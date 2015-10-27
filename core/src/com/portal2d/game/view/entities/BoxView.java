package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.view.ViewConstants;

import static com.portal2d.game.controller.Box2DConstants.PPM;

/**
 *
 */
public class BoxView extends EntityView<Box> {

    private Sprite sprite;

    public BoxView(Box box) {
        super(box);

        sprite = new Sprite(Portal2D.assets.getTexture(ViewConstants.TextureName.SPRITE_BOX));

        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    public void render(SpriteBatch batch) {

        batch.begin();

        float x = body.getPosition().x * PPM - width / 2;
        float y = body.getPosition().y * PPM - height / 2;

        //batch.draw(texture, x, y);

        sprite.setPosition(x, y);
        //sprite.rotate((float) Math.toDegrees(body.getAngle()));
        sprite.draw(batch);

        batch.end();
    }

}
