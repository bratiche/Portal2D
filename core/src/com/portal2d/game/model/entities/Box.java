package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.*;

/**
 * An entity that doesn't do anything. Basically dead weight to solve puzzles.
 */
public class Box extends DynamicEntity {

    public Box(Level level, Vector2 position) {
        super(level, position, EntityType.BOX);

        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        shape.setAsBox(BOX_WIDTH / 2, BOX_HEIGHT / 2);
        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.2f;
        fixtureDef.friction = 1f;
        //fixtureDef.density = 4f; // uncomment this to make the boxes rotate
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
    }

}
