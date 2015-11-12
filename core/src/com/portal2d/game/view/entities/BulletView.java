package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.view.ViewConstants;

import static com.portal2d.game.view.ViewConstants.PPM;

/**
 *
 */
public class BulletView extends EntityView<Bullet> {

    private Sprite sprite;

    public BulletView(Bullet bullet) {
        super(bullet);

        sprite = new Sprite(Portal2D.assets.getTexture(ViewConstants.TextureName.SPRITE_BULLET));

        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        float x = model.getPosition().x * PPM;
        float y = model.getPosition().y * PPM;

        batch.begin();
        sprite.setCenter(x, y);
        sprite.setRotation(model.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        batch.end();
    }
}
