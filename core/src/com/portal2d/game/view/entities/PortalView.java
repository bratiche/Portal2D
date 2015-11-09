package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.portals.Portal;

import java.util.NoSuchElementException;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class PortalView extends EntityView<Portal> {

    private Animation animation;
    private Sprite[] frames;

    public PortalView(Portal model) {
        super(model);

        Texture texture;
        TextureRegion[][] regions;

        switch(model.getColor()) {
            case BLUE:
                texture = Portal2D.assets.getTexture(TextureName.BLUE_PORTAL);
                break;
            case ORANGE:
                texture = Portal2D.assets.getTexture(TextureName.ORANGE_PORTAL);
                break;
            default:
                throw new NoSuchElementException();
        }


        width = PORTAL_WIDTH;
        height = PORTAL_HEIGHT;
        regions = TextureRegion.split(texture, PORTAL_WIDTH, PORTAL_HEIGHT);

        int framesAmount = 10;
        frames = new Sprite[framesAmount];

        for(int i = 0; i < framesAmount; i++) {
            frames[i] = new Sprite(regions[i][0]);
        }

        animation = new Animation(0.12f, frames);
    }

    private float stateTime = 0;

    @Override
    public void render(SpriteBatch batch, float deltaTime) {
        float x = model.getBody().getPosition().x * PPM;
        float y = model.getBody().getPosition().y * PPM;


        stateTime += deltaTime;

//        if(stateTime > animation.getAnimationDuration()) {
//            stateTime = 0;
//        }

        Sprite sprite = (Sprite) animation.getKeyFrame(stateTime, true);

        batch.begin();
        sprite.setCenter(x, y);
        sprite.setRotation(model.getNormal().angle() - MathUtils.radiansToDegrees * MathUtils.PI / 2);
        sprite.draw(batch);
        batch.end();
    }
}
