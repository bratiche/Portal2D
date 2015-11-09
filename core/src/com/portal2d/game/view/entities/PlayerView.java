package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.entities.Player.PlayerState;

import java.util.HashMap;
import java.util.Map;

import static com.portal2d.game.model.entities.Player.PlayerState.*;
import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class PlayerView extends EntityView<Player> {

    private Map<PlayerState, Animation> animations;
    private float stateTime;

    public PlayerView(Player model) {
        super(model);

        animations = new HashMap<PlayerState, Animation>();

        Texture texture = Portal2D.assets.getTexture(TextureName.ANIM_PLAYER);

        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;

        TextureRegion[][] sprites = TextureRegion.split(texture, PLAYER_WIDTH, PLAYER_HEIGHT);

        Animation walk = new Animation(ANIM_PLAYER_DELAY, sprites[4]);
        walk.setPlayMode(Animation.PlayMode.LOOP);
        animations.put(WALKING, walk);

        Animation jump= new Animation(ANIM_PLAYER_DELAY, sprites[5][2]);
        animations.put(JUMPING, jump);

        Animation dead= new Animation(ANIM_PLAYER_DELAY, sprites[2]);
        animations.put(DEAD, dead);

        Animation stand= new Animation(ANIM_PLAYER_DELAY, sprites[8]);
        animations.put(STANDING, stand);

        Animation fall= new Animation(ANIM_PLAYER_DELAY, sprites[5][2]);
        animations.put(FALLING, fall);
    }

    private Sprite sprite = new Sprite();

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        TextureRegion keyFrame;

        stateTime += deltaTime;

        if(model.isWalking()) {

            if(stateTime > animations.get(WALKING).getAnimationDuration()) {
                stateTime = 0;
            }
            keyFrame = animations.get(WALKING).getKeyFrame(stateTime);
        }

        else if(model.isJumping()) {
            if(stateTime > animations.get(JUMPING).getAnimationDuration()) {
                stateTime=0;
            }
            keyFrame = animations.get(JUMPING).getKeyFrame(stateTime);
        }

        else if( model.isFalling()) {
            if(stateTime > animations.get(FALLING).getAnimationDuration()) {
                stateTime = 0;
            }
            keyFrame = animations.get(FALLING).getKeyFrame(stateTime);
        }

        else {
            stateTime = 0;
            keyFrame = animations.get(STANDING).getKeyFrame(stateTime);
        }

        batch.begin();
        if(!model.isFacingRight()) {
            sprite.setRegion(keyFrame);
            sprite.flip(true, false);

            batch.draw(sprite, model.getBody().getPosition().x * PPM - width / 2, model.getBody().getPosition().y * PPM - height / 2);
        }

        else {
            batch.draw(keyFrame, model.getBody().getPosition().x * PPM - width / 2, model.getBody().getPosition().y * PPM - height / 2);
        }
        batch.end();

    }
}