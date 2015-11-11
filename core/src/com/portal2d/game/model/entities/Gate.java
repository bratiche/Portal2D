package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.portal2d.game.model.entities.enemies.Bullet;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.GATE_HEIGHT;
import static com.portal2d.game.model.ModelConstants.GATE_WIDTH;

/**
 * An obstacle that can be overcome by pressing all it's {@link Switch}es.
 */
public class Gate extends StaticEntity implements Switchable {

    private boolean open;

    /** The number of switches this gate has */
    private int switches;

    public Gate(Level level, Vector2 position, int switches) {
        super(level, position, EntityType.GATE);

        if(switches < 0) {
            throw new IllegalArgumentException("There can't be a negative amount of switches.");
        }

        this.switches = switches;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(GATE_WIDTH / 2,  GATE_HEIGHT / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
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

    /** The bullet is destroyed only if this gate is closed. */
    @Override
    public void beginInteraction(Bullet bullet) {
        if(!open)
            level.addToRemove(bullet);
    }

    @Override
    public void update() {
        if(switches == 0 && !open) {
            open = true;
            this.setSensor(true);
        }
        else if (switches > 0 && open) {
            open = false;
            this.setSensor(false);
        }
    }

    @Override
    public void switchOn() {
        switches--;
    }

    @Override
    public void switchOff() {
        switches++;
    }

    public boolean isOpen() {
        return open;
    }

}
