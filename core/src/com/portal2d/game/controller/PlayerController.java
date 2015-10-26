package com.portal2d.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.portal2d.game.model.level.Level;
import com.portal2d.game.controller.states.PlayState;
import com.portal2d.game.model.entities.Player;

import static com.portal2d.game.controller.Box2DConstants.PPM;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_HEIGHT;
import static com.portal2d.game.view.ViewConstants.VIEWPORT_WIDTH;

/**
 *
 */
public class PlayerController extends InputAdapter {

    private PlayState playState;

    private World world;
    private Player player;

    private Vector3 mouse;

    //player stuff
    private Body playerBody;
    private Fixture playerPhysicsFixture;

    private final float MAX_VELOCITY = 2.0f;
    private boolean jump = false;

    private float stillTime = 0.0f;
    private long lastGroundTime = 0;

    public PlayerController(PlayState playState, Level level) {
        this.playState = playState;
        setLevel(level);

        mouse = new Vector3();
        Gdx.input.setInputProcessor(this);
    }

    public void handleInput() {

        Vector2 position = playerBody.getPosition();
        Vector2 velocity = playerBody.getLinearVelocity();

        boolean grounded = isPlayerGrounded();

        if(grounded) {
            lastGroundTime = TimeUtils.nanoTime();
        }
        else {
            if(TimeUtils.nanoTime() - lastGroundTime < 100000000)
                grounded = true;
        }

        //cap max velocity
        if(Math.abs(velocity.x) > MAX_VELOCITY) {
            velocity.x = Math.signum(velocity.x) * MAX_VELOCITY;
            playerBody.setLinearVelocity(velocity.x, velocity.y);
        }

        //calculate stillTime and damp
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            stillTime += Gdx.graphics.getDeltaTime();
            playerBody.setLinearVelocity(velocity.x * 0.9f, velocity.y);
        } else {
            stillTime = 0;
        }

        //disable friction while jumping
        if(!grounded) {
            playerPhysicsFixture.setFriction(0.0f);
            //playerSensorFixture.setFriction(0.0f);
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
        if (Gdx.input.isKeyPressed(Input.Keys.A) && velocity.x > -MAX_VELOCITY) {
            playerBody.applyLinearImpulse(-2f, 0, position.x, position.y, true);
        }

        // apply right impulse if max velocity is not reached yet
        if (Gdx.input.isKeyPressed(Input.Keys.D) && velocity.x < MAX_VELOCITY) {
            playerBody.applyLinearImpulse(2f, 0, position.x, position.y, true);
        }

        // jump, but only when grounded
        if (jump) {
            jump = false;
            if (grounded) {
                playerBody.setLinearVelocity(velocity.x, 0);
                //System.out.println("Velocity before jump: " + playerBody.getLinearVelocity());
                playerBody.setTransform(position.x, position.y + 0.01f, 0);
                playerBody.applyLinearImpulse(0, 4, position.x, position.y, true);
                //System.out.println("jump velocity, " + playerBody.getLinearVelocity());
            }
        }

        if(playerBody.getLinearVelocity().x >= 0) {
            player.setFacingRight(true);
        }
        else {
            player.setFacingRight(false);
        }

        playerBody.setAwake(true);

        //check mouse input
        if(Gdx.input.justTouched()) {

            //screen coordinates
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();

            playState.getCamera().unproject(mouse, 0, 0, VIEWPORT_WIDTH / PPM, VIEWPORT_HEIGHT / PPM);

            //translate to world coordinates
            float dx = playState.getCamera().position.x * PPM - VIEWPORT_WIDTH / 2;
            float dy = playState.getCamera().position.y * PPM - VIEWPORT_HEIGHT / 2;

            mouse.add(dx, dy, 0);
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                player.getWeapon().actionLeftClick(new Vector2(mouse.x / PPM, mouse.y / PPM));
            }
            if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                player.getWeapon().actionRightClick(new Vector2(mouse.x / PPM, mouse.y / PPM));
            }

        }

    }

    private boolean isPlayerGrounded() {
        Array<Contact> contacts = world.getContactList();
        for(int i =0; i < contacts.size; i++) {
            Contact contact = contacts.get(i);
            if(contact.isTouching() && (contact.getFixtureA() == playerPhysicsFixture || contact.getFixtureB() == playerPhysicsFixture)) {
                Vector2 position = playerBody.getPosition();
                WorldManifold manifold = contact.getWorldManifold();
                boolean below = true;
                for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                    below &= (manifold.getPoints()[j].y < position.y - 1.5f / Box2DConstants.PPM);
                }
                if(below) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean keyDown (int keycode) {
        if(keycode == Input.Keys.W)
            jump = true;
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
