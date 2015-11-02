package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.ui.Text;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;
import static com.portal2d.game.view.ViewConstants.TEXTBUTTON_HEIGHT;

/**
 * Created by matias on 01/11/15.
 */
public class InstructionScene extends Scene{

        private Texture background;
        private OrthographicCamera camera;
        private TextButton b;
        private Text description;

        public InstructionScene() {

            background = Portal2D.assets.getTexture(TextureName.INSTRUCTIONS_BG);
            camera = new OrthographicCamera();
            camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

            // font setting
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/font/font.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = INSTRUCTION_SIZE; // setting font size
            BitmapFont font = generator.generateFont(parameter);
            generator.dispose();


            b = new TextButton(FIRST_BUTTON_INSTRUCTIONS_X,FIRST_BUTTON_INSTRUCTIONS_Y, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Back", font);
            description= new Text(INSTRUCTION_TEXT_POSITION_X, INSTRUCTION_TEXT_POSITION_Y,"Welcome to Portal2D.\nThis game is a puzzle style game where you will have\nto find your way through the map using portals.\nOnly two portals can be active simultaneously, you can\nexit and entry in both.\nBoxes can be used to activate gates(both in color RED).\nUse W,A,S,D to move, right and left click to create portals,\nP to pause.\nPortals only can be created on portable surfaces, distinguished\nby color WHITE.\nENJOY!" , font);
        }

        public OrthographicCamera getCamera() {
            return camera;
        }

        @Override
        public void render(SpriteBatch batch) {

            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            batch.draw(background, 0, 0);
            batch.end();

            Vector3 mouse = new Vector3();
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();

            camera.unproject(mouse);

            b.render(batch, mouse.x, mouse.y);
            description.render(batch, 0, 0);


        }

        public TextButton getBackbutton() {
            return b;
        }



}
