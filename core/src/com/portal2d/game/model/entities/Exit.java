package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;

import static com.portal2d.game.model.ModelConstants.EXIT_HEIGHT;
import static com.portal2d.game.model.ModelConstants.EXIT_WIDTH;

/**
 * An entity the player has to get to to change the current level.
 */
public class Exit extends StaticEntity {

    private LevelName destinyLevel;

    public Exit(Level level, Vector2 position, LevelName destinyLevel) {
        super(level, position, EntityType.EXIT);
        this.destinyLevel = destinyLevel;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(EXIT_WIDTH / 2, EXIT_HEIGHT / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
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

    @Override
    public void beginInteraction(Player player) {
        level.setNextLevel(this);
    }

    public LevelName getDestinyLevel() {
        return destinyLevel;
    }

}
