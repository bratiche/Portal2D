package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.Exit;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class ExitView extends EntityView<Exit> {

    private Texture texture;

    public ExitView(Exit model) {
        super(model);

        texture = Portal2D.assets.getTexture(TextureName.SPRITE_EXIT);

        width = EXIT_WIDTH;
        height = EXIT_HEIGHT;
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {

        float x = body.getPosition().x * PPM - width;
        float y = body.getPosition().y * PPM - height / 2;

        batch.begin();

//        if(model.isReached()) {
//            //cut scene
//        }
//        else
//            batch.draw(texture, x, y);

        batch.end();

    }
}
