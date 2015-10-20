package com.portal2d.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.controller.level.Level;

/**
 *
 */
public class PlayController {

    private World world;
    private Level level;
    private Player player;
    private PlayState state;

    //movement test
    private Vector2 jumpForce = new Vector2(0, 300);
    float max_x_velocity = 3;

    public PlayController(PlayState state, Level level) {
        this.state = state;
        player = level.getPlayer();

        player.getBody().setAwake(true);
        player.getBody().setActive(true);
    }

    //TODO: fix spider-man like climbing walls
    public void handleInput() {

        if(player.getBody().getLinearVelocity().y != 0)
            player.setJumping(true);
        else
            player.setJumping(false);

        if(player.getBody().getLinearVelocity().x >= 0)
            player.setFacingRight(true);
        else
            player.setFacingRight(false);

        if(Gdx.input.isKeyPressed(Input.Keys.W) && !player.isJumping())
            player.getBody().applyForceToCenter(jumpForce, true);

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().add(-1, 0));
        else if(Gdx.input.isKeyPressed(Input.Keys.D))
            player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().add(1, 0));
        else if(!player.isJumping())
            player.getBody().setLinearVelocity(0, 0);

        if(player.isJumping() && player.getBody().getLinearVelocity().x != 0) {
            Vector2 airFriction = new Vector2(7, 0);
            airFriction.scl(player.isFacingRight() ? -1 : 1);
            player.getBody().applyForceToCenter(airFriction, true);
        }

        if(player.getBody().getLinearVelocity().x >= max_x_velocity)
            player.getBody().setLinearVelocity(max_x_velocity, player.getBody().getLinearVelocity().y);
        else if(player.getBody().getLinearVelocity().x <= -max_x_velocity)
            player.getBody().setLinearVelocity(-max_x_velocity, player.getBody().getLinearVelocity().y);

    }

    public void setLevel(Level level) {
        this.level = level;
    }

}
