package com.portal2d.game.view.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.model.entities.enemies.Turret;

/**
 *
 */
public class TurretView extends EntityView<Turret> {

    public TurretView(Turret model) {
        super(model);
    }

    @Override
    public void render(SpriteBatch batch, float deltaTime) {
    }
}
