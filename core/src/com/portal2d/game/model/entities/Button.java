package com.portal2d.game.model.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.portal2d.game.model.level.Level;

/**
 * Sends an order to it's {@link #switchable} whenever it's pressed or released.
 */
public class Button extends Switch {

    private boolean pressed;
    private int interactions;

    public Button(Level level, Vector2 position, Switchable linkedEntity) {
        super(level, position, EntityType.BUTTON, linkedEntity);

        ButtonShape shape = new ButtonShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        setPosition(position);

        shape.dispose();
    }

    @Override
    public void update() {
        if(interactions > 0 && !pressed) {
            pressed = true;
            switchable.switchOn();
        }
        else if(interactions == 0 && pressed) {
            pressed = false;
            switchable.switchOff();
        }
    }

    @Override
    public void beginInteraction(Entity entity) {
        entity.beginInteraction(this);
        interactions++;
    }

    @Override
    public void endInteraction(Entity entity) {
        entity.endInteraction(this);
        interactions--;
    }

    public boolean isPressed() {
        return pressed;
    }


    /** Defines the shape of the Button. */
    private class ButtonShape extends PolygonShape {

        /** Vertices relative to the position of the button*/
        float[] vertices = new float[8];

        ButtonShape() {
            vertices[0] = 0;
            vertices[1] = 0;
            vertices[2] = 0.2f;
            vertices[3] = 0.145f;
            vertices[4] = 1f;
            vertices[5] = 0.145f;
            vertices[6] = 1.2f;
            vertices[7] = 0;

            set(vertices);
        }
    }

}
