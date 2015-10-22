package com.portal2d.game.model.entities.portals;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.model.entities.*;

/**
 * Special surface able to create portals on it
 */
public class PortableSurface extends Surface {

    public PortableSurface(World world, Body body) {
        super(world, body);
    }

    @Override
    public void interact(Box box) {

    }

    @Override
    public void interact(Player player) {

    }

    @Override
    public void interact(Exit exit) {

    }

    @Override
    public void interact(Button button) {

    }

    @Override
    public void interact(Tile tile) {

    }

}
