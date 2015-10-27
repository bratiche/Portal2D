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

    private float width;
    private float height;

    public BoxView(Box box) {
        super(box);
        this.body = box.getBody();
        sprite = new Sprite(Portal2D.assets.getTexture(ViewConstants.TextureName.CUBE));

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
