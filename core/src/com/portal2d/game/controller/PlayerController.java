package com.portal2d.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.Player;
import com.portal2d.game.model.level.Level;

import static com.portal2d.game.model.ModelConstants.PLAYER_MAX_VELOCITY;
import static com.portal2d.game.model.entities.Player.PlayerState.*;
import static com.portal2d.game.view.ViewConstants.*;

/**
 * Controls the {@link Player} according to user input.
 */
public class PlayerController extends InputAdapter {

    private PlayState playState;
    private Vector3 mouse;

    private World world;
    private Player player;

    //player stuff
    private Body playerBody;
    private Fixture playerPhysicsFixture;

    private boolean jump = false;

    private float stillTime = 0.0f;
    private long lastGroundTime = 0;

    private Vector2 position;
    private Vector2 velocity;
    private boolean grounded;

    public PlayerController(PlayState playState) {
        this.playState = playState;

        mouse = new Vector3();
        Gdx.input.setInputProcessor(this);
    }

    public void handleInput() {

        if(player.isDead()) {
            return;
        }

        position = playerBody.getPosition();
        velocity = playerBody.getLinearVelocity();

        grounded = isPlayerGrounded();

        if(grounded) {
            lastGroundTime = TimeUtils.nanoTime();
        }
        else if(TimeUtils.nanoTime() - lastGroundTime < 100000000) {
            grounded = true;
        }

        movePlayer();

        setPlayerState();

        checkMouseInput();
    }

    /**
     * @return whether the player is above another object.
     */
    private boolean isPlayerGrounded() {
        Array<Contact> contacts = world.getContactList();
        for(int i =0; i < contacts.size; i++) {
            Contact contact = contacts.get(i);
            if(contact.isTouching() && (contact.getFixtureA() == playerPhysicsFixture || contact.getFixtureB() == playerPhysicsFixture)) {
                Vector2 position = playerBody.getPosition();
                WorldManifold manifold = contact.getWorldManifold();
                boolean below = true;

                // Avoid the player to jump when is on a sensor
                if((contact.getFixtureA().isSensor() || contact.getFixtureB().isSensor())) {
                    below = false;
                }
                for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                    below &= (manifold.getPoints()[j].y < position.y - 1.5f / PPM);
                }
                return below;
            }
        }
        return false;
    }

    /**
     * Moves the player's body.
     */
    private void movePlayer() {
        //calculate stillTime and damp
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            stillTime += Gdx.graphics.getDeltaTime();
            playerBody.setLinearVelocity(velocity.x * 0.9f, velocity.y);
        } else {
            if(Math.abs(velocity.x) > PLAYER_MAX_VELOCITY) {
                stillTime = 0;
            }
        }

        //disable friction while jumping
        if(!grounded) {
            playerPhysicsFixture.setFriction(0.0f);
        }
        else {
            if(!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && stillTime > 0.2f) {
                playerPhysicsFixture.setFriction(1000.0f);
            }
            else {
                playerPhysicsFixture.setFriction(0.2f);
            }
        }

        Array<Contact> contacts = world.getContactList();
        for(int i = 0; i < world.getContactCount(); i++) {
            Contact contact = contacts.get(i);
            contact.resetFriction();
        }

        // apply left impulse if max velocity is not reached yet
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerBody.applyLinearImpulse(-2f, 0, position.x, position.y, true);
            //cap max velocity
            if (Math.abs(velocity.x) > PLAYER_MAX_VELOCITY) {
                velocity.x = Math.signum(velocity.x) * PLAYER_MAX_VELOCITY;
                playerBody.setLinearVelocity(velocity.x, velocity.y);
            }
        }

        // apply right impulse if max velocity is not reached yet
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerBody.applyLinearImpulse(2f, 0, position.x, position.y, true);
            //cap max velocity
            if (Math.abs(velocity.x) > PLAYER_MAX_VELOCITY) {
                velocity.x = Math.signum(velocity.x) * PLAYER_MAX_VELOCITY;
                playerBody.setLinearVelocity(velocity.x, velocity.y);
            }
        }

        // jump, but only when grounded
        if (jump) {
            jump = false;
            if (grounded) {
                playerBody.setLinearVelocity(velocity.x, 0);
                playerBody.setTransform(position.x, position.y + 0.01f, 0);
                playerBody.applyLinearImpulse(0, 4, position.x, position.y, true);
            }
        }
    }

    /**
     * Sets the current state of the player.
     */
    private void setPlayerState() {
        if(grounded) {
            //walking
            if(Math.abs(playerBody.getLinearVelocity().x) > 0.01f) {
                player.setState(WALKING);
            }
            //standing
            else {
                player.setState(STANDING);
            }
        }
        else {
            if(playerBody.getLinearVelocity().y > 0.01f) {
                player.setState(JUMPING);
            }
            else if (playerBody.getLinearVelocity().y < -0.01f) {
                player.setState(FALLING);
            }
            else {
                player.setState(STANDING);
            }
        }
    }

    private final Vector2 tmp = new Vector2();

    private void checkMouseInput() {
        //screen coordinates
        mouse.x = Gdx.input.getX();
        mouse.y = Gdx.input.getY();

        playState.getBox2DCamera().unproject(mouse, 0, 0, VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);

        //translate to world coordinates
        float dx = playState.getBox2DCamera().position.x * PPM - VIEWPORT_WIDTH / 2;
        float dy = playState.getBox2DCamera().position.y * PPM - VIEWPORT_HEIGHT / 2;

        mouse.add(dx, dy, 0);

        // Set if the player is facing right or left (regardless of whether it is moving or jumping)
        // The player faces right or left according to the position of the mouse
        player.setFacingRight(mouse.x / PPM >= playerBody.getPosition().x);


        // Shoot
        if(Gdx.input.justTouched()) {
            tmp.set(mouse.x / PPM, mouse.y / PPM);
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                player.getPortalGun().actionLeftClick(tmp);
            }
            else if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                player.getPortalGun().actionRightClick(tmp);
            }
        }

        // Grab or drop an entity
        if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            if(player.getPortalGun().canGrabEntity()) {
                player.getPortalGun().queryAABB();
            }
            else {
                player.getPortalGun().dropEntity();
            }
        }

        // Update the portal gun
        tmp.set(mouse.x / PPM, mouse.y / PPM);
        player.getPortalGun().update(tmp);

        // Destroy portals
        if(Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            player.getPortalGun().destroyPortals();
        }
    }

    @Override
    public boolean keyDown (int keycode) {
        if(keycode == Input.Keys.W) {
            jump = true;
        }
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        if(keycode == Input.Keys.W)
            jump = false;
        return false;
    }

    public void setLevel(Level level) {
        player = level.getPlayer();
        world = level.getWorld();

        playerBody = player.getBody();
        playerPhysicsFixture = playerBody.getFixtureList().get(0);
    }

}
