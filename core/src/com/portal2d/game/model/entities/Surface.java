package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.view.ViewConstants.PPM;

/**
 * Represents a wall or the floor, it's an obstacle that cannot be crossed by.
 */
public class Surface extends StaticEntity {

    protected float width;
    protected float height;

    public Surface(Level level, Vector2 position, float width, float height, EntityType type) {
        super(level, position, type);
        createBounds(width, height);
    }

    public Surface(Level level, Vector2 position, float width, float height) {
        super(level, position, EntityType.SURFACE);
        createBounds(width, height);
    }

    private void createBounds(float width, float height) {
        this.width = width;
        this.height = height;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.6f;
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
