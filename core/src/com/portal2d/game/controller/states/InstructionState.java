package com.portal2d.game.controller.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.states.GameState;
import com.portal2d.game.view.scenes.InstructionScene;
import com.portal2d.game.view.scenes.PauseScene;

/**
 * Created by matias on 01/11/15.
 */
public class InstructionState extends GameState {

        private InstructionScene scene;

        public InstructionState(GameStateManager gsm) {
            super(gsm);
        }

        @Override
        public void entered() {
            scene = new InstructionScene();
        }

        @Override
        public void handleInput() {

            mouse.x= Gdx.input.getX();
            mouse.y= Gdx.input.getY();

            scene.getCamera().unproject(mouse);

            if(scene.getBackbutton().isClicked(mouse.x , mouse.y)) {
                gsm.pop();
            }


        }

        @Override
        public void update(float dt) {
        }

        @Override
        public void render(SpriteBatch batch) {
            mouse.x= Gdx.input.getX();
            mouse.y= Gdx.input.getY();

            scene.getCamera().unproject(mouse);


            // Render this state
            batch.setProjectionMatrix(scene.getCamera().combined);
            scene.render(batch);

        }

        @Override
        public void leaving() {
            
        }

}
