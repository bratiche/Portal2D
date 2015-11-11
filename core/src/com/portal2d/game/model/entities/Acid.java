package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.portal2d.game.model.level.Level;

/**
 * Entity that destroys anything that touches it.
 */
public class Acid extends StaticEntity {

    public Acid(Level level, Vector2 position, float width, float height) {
        super(level, position, EntityType.ACID);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 , height / 2 );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        level.addToRemove(entity);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

    @Override
    public void beginInteraction(Player player) {
        player.die();
    }
}
