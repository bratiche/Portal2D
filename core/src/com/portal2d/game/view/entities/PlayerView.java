package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.view.ViewConstants;

import static com.portal2d.game.controller.Box2DConstants.PPM;
import static com.portal2d.game.view.ViewConstants.*;

/**
 * Created by matias on 28/10/15.
 */
public class PlayerView extends EntityView<Player> {

    private float stateTime;
    private boolean lastFrameFacingRight = true;

    public PlayerView(Player model) {
        super(model);

        Texture texture = Portal2D.assets.getTexture(ViewConstants.TextureName.ANIM_PLAYER);

        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;

        TextureRegion[][] sprites = TextureRegion.split(texture, PLAYER_WIDTH, PLAYER_HEIGHT);

        Animation walk = new Animation(ANIM_PLAYER_DELAY, sprites[4]);
        walk.setPlayMode(Animation.PlayMode.LOOP);
        animations.put(Action.PLAYER_WALK, walk);

        Animation jump= new Animation(ANIM_PLAYER_DELAY, sprites[5][2]);
        animations.put(Action.PLAYER_JUMP, jump);

        Animation dead= new Animation(ANIM_PLAYER_DELAY, sprites[2]);
        animations.put(Action.PLAYER_DEAD, dead);

        Animation stand= new Animation(ANIM_PLAYER_DELAY, sprites[8]);
        animations.put(Action.PLAYER_STAND, stand);

        Animation fall= new Animation(ANIM_PLAYER_DELAY, sprites[5][2]);
        animations.put(Action.PLAYER_FALL, fall);
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {


        TextureRegion keyFrame;

        stateTime += deltaTime;

        if(model.isWalking()) {

            if(stateTime > animations.get(Action.PLAYER_WALK).getAnimationDuration()) {
                stateTime = 0;
            }
            keyFrame = animations.get(Action.PLAYER_WALK).getKeyFrame(stateTime);
        }

        else if(model.isJumping()) {

            if(stateTime > animations.get(Action.PLAYER_JUMP).getAnimationDuration()) {
                stateTime=0;
            }
            keyFrame = animations.get(Action.PLAYER_JUMP).getKeyFrame(stateTime);
        }

        //TODO: is falling
        else if( model.isFalling()) {
            System.out.println("falling");
            if(stateTime > animations.get(Action.PLAYER_FALL).getAnimationDuration()) {
                stateTime = 0;
            }
            keyFrame = animations.get(Action.PLAYER_FALL).getKeyFrame(stateTime);
        }

        //standing
        else {
            stateTime = 0;
            keyFrame = animations.get(Action.PLAYER_STAND).getKeyFrame(stateTime);
        }

        batch.begin();
        if(!model.isFacingRight()) {
            Sprite sprite = new Sprite(keyFrame);
            sprite.flip(true, false);

            batch.draw(sprite, body.getPosition().x * PPM - width / 2, body.getPosition().y * PPM - height / 2);
        }

        else {
            batch.draw(keyFrame, body.getPosition().x * PPM - width / 2, body.getPosition().y * PPM - height / 2);
        }
        batch.end();
    }
}