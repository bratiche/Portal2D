package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.view.ViewConstants;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class PortalView extends EntityView<Portal> {

    private Sprite sprite;

    public PortalView(Portal model) {
        super(model);

        //TODO switch
        Texture texture;
        if(model.getColor() == PortalColor.BLUE) {
            texture = Portal2D.assets.getTexture(TextureName.BLUE_PORTAL);
        }
        else if(model.getColor() == PortalColor.ORANGE) {
            texture = Portal2D.assets.getTexture(TextureName.ORANGE_PORTAL);
        }
        else {
            texture = null;
        }

        width = PORTAL_WIDTH;
        height = PORTAL_HEIGHT;
        sprite = new Sprite(texture);
        sprite.setSize(width, height);

    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {
        float x = body.getPosition().x * PPM - width / 2;
        float y = body.getPosition().y * PPM - height / 2;

        //TODO calculate normal
        batch.begin();
        sprite.setPosition(x, y);
        sprite.draw(batch);
        batch.end();
    }
}
