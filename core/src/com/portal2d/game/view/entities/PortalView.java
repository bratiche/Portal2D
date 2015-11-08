package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.portal2d.game.Portal2D;
import com.portal2d.game.model.entities.portals.Portal;
import com.portal2d.game.model.entities.portals.PortalColor;
import com.portal2d.game.view.ViewConstants;

import java.util.NoSuchElementException;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class PortalView extends EntityView<Portal> {

    private Sprite sprite;

    public PortalView(Portal model) {
        super(model);

        Texture texture;

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
        sprite = new Sprite(texture);
        sprite.setSize(width, height);

    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {
        float x = model.getBody().getPosition().x * PPM;
        float y = model.getBody().getPosition().y * PPM;

        batch.begin();
        sprite.setCenter(x, y);
        sprite.setRotation(model.getNormal().angle() - MathUtils.radiansToDegrees * MathUtils.PI / 2);
        sprite.draw(batch);
        batch.end();
    }
}
