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

    private float stateTime;

    public GateView(Gate model) {
        super(model);

        Texture texture = Portal2D.assets.getTexture(TextureName.ANIM_GATE);

        width = GATE_WIDTH;
        height = GATE_HEIGHT;

        TextureRegion[][] sprites = TextureRegion.split(texture, GATE_WIDTH, GATE_HEIGHT);

        Animation open = new Animation(1 / 2f, sprites[0]);
        animations.put(Action.GATE_OPEN, open);

        //close not used
        Animation close = new Animation(1 / 2f, sprites[0]);
        close.setPlayMode(Animation.PlayMode.REVERSED);

        animations.put(Action.GATE_CLOSE, close);
    }

    @Override
    public void render(SpriteBatch batch) {

        TextureRegion keyFrame;

        //TODO: pass delta time as parameter

        if(model.isOpen()) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        else {
            stateTime = 0;
        }

        keyFrame = animations.get(Action.GATE_OPEN).getKeyFrame(stateTime);
        batch.begin();
        batch.draw(keyFrame, body.getPosition().x * PPM - width / 2, body.getPosition().y * PPM - height / 2);
        batch.end();
    }

}
