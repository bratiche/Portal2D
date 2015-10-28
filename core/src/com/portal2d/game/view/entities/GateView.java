package com.portal2d.game.view.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Gate;

import static com.portal2d.game.controller.Box2DConstants.PPM;
import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class GateView extends EntityView<Gate> {

    //we need to have one stateTime for each animation
    private float stateTime;

    public GateView(Gate model) {
        super(model);

        Texture texture = Portal2D.assets.getTexture(TextureName.ANIM_GATE);

        width = GATE_WIDTH;
        height = GATE_HEIGHT;

        TextureRegion[][] sprites = TextureRegion.split(texture, GATE_WIDTH, GATE_HEIGHT);

        //in this case the map is not necessary since we only have one animation
        Animation open = new Animation(ANIM_GATE_DELAY, sprites[0]);
        animations.put(Action.GATE_OPEN, open);

    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        TextureRegion keyFrame;

        if(model.isOpen()) {
            stateTime += deltaTime;
            if(stateTime > animations.get(Action.GATE_OPEN).getAnimationDuration())
                stateTime = animations.get(Action.GATE_OPEN).getAnimationDuration();
        }
        else {
//            stateTime -= deltaTime;
//            if(stateTime < 0)
                stateTime = 0;
        }

        keyFrame = animations.get(Action.GATE_OPEN).getKeyFrame(stateTime);

        batch.begin();
        batch.draw(keyFrame, body.getPosition().x * PPM - width / 2, body.getPosition().y * PPM - height / 2);
        batch.end();
    }

}
