package com.portal2d.game.tests;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Box;
import com.portal2d.game.model.entities.Button;
import com.portal2d.game.model.entities.Entity;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ButtonTest {

    private Button button;
    private Entity entity;

    @Before
    public void initialize() {
        Level level = new Level(LevelName.LEVEL_0);

        button = new Button(level, new Vector2(), null);

        entity = new Box(level, new Vector2());
    }

    @Test(expected = NullPointerException.class)
    public void testStateChange() {
        assertFalse(button.isPressed());
        button.beginInteraction(entity);
        button.update();
        assertTrue(button.isPressed());
        button.endInteraction(entity);
        button.update();
        assertFalse(button.isPressed());
    }

    @Test(expected = NullPointerException.class)
    public void testRepeatedInteractions() {
        assertFalse(button.isPressed());
        button.beginInteraction(entity);
        button.beginInteraction(entity);
        button.beginInteraction(entity);
        button.update();
        assertTrue(button.isPressed());
        button.endInteraction(entity);
        button.endInteraction(entity);
        button.endInteraction(entity);
        button.update();
        assertFalse(button.isPressed());
    }

    @Test
    public void testNegativeInteractions(){
        button.endInteraction(entity);
        button.update();
        assertFalse(button.isPressed());
    }

}
