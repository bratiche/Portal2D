package com.portal2d.game.tests;

import com.badlogic.gdx.math.Vector2;
import com.portal2d.game.model.entities.Gate;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.model.level.LevelName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GateTest {

    private Gate gate;
    private final int switches = 3;

    @Before
    public void initialize() {
        Level level = new Level(LevelName.LEVEL_0);

        gate = new Gate(level, new Vector2(), switches);
    }

    @Test
    public void testStateChange() {

        for(int i = 0; i < switches; i++) {
            assertFalse(gate.isOpen());
            gate.switchOn();
            gate.update();
        }
        assertTrue(gate.isOpen());
    }

}