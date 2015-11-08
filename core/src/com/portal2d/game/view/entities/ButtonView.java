package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Button;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class ButtonView extends EntityView<Button> {

    private TextureRegion[][] regions;

    public ButtonView(Button model) {
        super(model);

        Texture texture = Portal2D.assets.getTexture(TextureName.SPRITE_BUTTON);
        regions = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight());

        width = BUTTON_WIDTH;
        height = BUTTON_HEIGHT;
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        float x = model.getBody().getPosition().x * PPM;
        float y = model.getBody().getPosition().y * PPM;

        batch.begin();
        if(model.isPressed()) {
            batch.draw(regions[0][1], x, y);
        }
        else {
            batch.draw(regions[0][0], x, y);
        }
        batch.end();

    }
}
