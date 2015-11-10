package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Gate;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class GateView extends EntityView<Gate> {

    private Animation open;
    private float stateTime;

    public GateView(Gate model) {
        super(model);

        Texture texture = Portal2D.assets.getTexture(TextureName.ANIM_GATE);

        width = GATE_WIDTH;
        height = GATE_HEIGHT;

        TextureRegion[][] sprites = TextureRegion.split(texture, GATE_WIDTH, GATE_HEIGHT);

        open = new Animation(ANIM_GATE_DELAY, sprites[0]);
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        TextureRegion keyFrame;

        if(model.isOpen()) {
            stateTime += deltaTime;
            if(stateTime > open.getAnimationDuration())
                stateTime = open.getAnimationDuration();
        }
        else {
            //to play in reverse mode:
//            stateTime -= deltaTime;
//            if(stateTime < 0)
                stateTime = 0;
        }

        keyFrame = open.getKeyFrame(stateTime);
        batch.begin();
        batch.draw(keyFrame, model.getPosition().x * PPM - width / 2, model.getPosition().y * PPM - height / 2);
        batch.end();
    }

}
