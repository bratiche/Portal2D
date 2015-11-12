package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.view.ViewConstants;

import static com.portal2d.game.view.ViewConstants.BOX_HEIGHT;
import static com.portal2d.game.view.ViewConstants.BOX_WIDTH;
import static com.portal2d.game.view.ViewConstants.PPM;

/**
 *
 */
public class BoxView extends EntityView<Box> {

    private Sprite sprite;

    public BoxView(Box box) {
        super(box);

        sprite = new Sprite(Portal2D.assets.getTexture(ViewConstants.TextureName.SPRITE_BOX));

        width = BOX_WIDTH;
        height = BOX_HEIGHT;
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        batch.begin();

        float x = model.getPosition().x * PPM;
        float y = model.getPosition().y * PPM;

        sprite.setCenter(x, y);
        sprite.setRotation(model.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);

        batch.end();
    }

}
