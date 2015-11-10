package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.enemies.Turret;

import static com.portal2d.game.view.ViewConstants.PPM;
import static com.portal2d.game.view.ViewConstants.TextureName;

/**
 *
 */
public class TurretView extends EntityView<Turret> {

    private Sprite shooting;
    private Sprite eye;
    private Sprite idle;

    public TurretView(Turret model) {
        super(model);
        Texture texture = Portal2D.assets.getTexture(TextureName.SPRITE_TURRET);

        TextureRegion[][] sprites = TextureRegion.split(texture, texture.getWidth() / 3, texture.getHeight());

        shooting = new Sprite(sprites[0][0]);
        eye = new Sprite(sprites[0][1]);
        idle = new Sprite(sprites[0][2]);
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        if(model.targetLockedOn()) {
            shooting.setCenter(model.getPosition().x * PPM, model.getPosition().y * PPM);
            Vector2 direction = model.getTargetPosition();
            direction.sub(model.getPosition());
            direction.nor();

            float dx = 4 * direction.x;
            float dy = 4 * direction.y;
            eye.setCenter(model.getPosition().x * PPM + dx, model.getPosition().y * PPM + dy);
            batch.begin();
            shooting.draw(batch);
            eye.draw(batch);
            batch.end();
        }

        else {
            idle.setCenter(model.getPosition().x * PPM, model.getPosition().y * PPM);
            batch.begin();
            idle.draw(batch);
            batch.end();
        }

    }
}
