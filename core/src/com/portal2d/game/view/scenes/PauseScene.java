package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.portal2d.game.view.ui.TextButton;

import static com.portal2d.game.view.ViewConstants.*;
import static com.portal2d.game.view.ViewConstants.TEXTBUTTON_HEIGHT;

/**
 * Created by matias on 01/11/15.
 */
public class PauseScene extends Scene {

    private OrthographicCamera camera;
    private TextButton i;
    private TextButton c;
    private TextButton e;

    public PauseScene() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

        // font setting
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal("core/assets/sprites/fontE.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FORMAT_SIZE; // setting font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        c=new TextButton(FIRST_BUTTON_START_PAUSE, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Continue", font);
        i=new TextButton(FIRST_BUTTON_START_PAUSE - SPACE_BETWEEN_BUTTONS, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Instructions", font);
        e=new TextButton(FIRST_BUTTON_START_PAUSE - SPACE_BETWEEN_BUTTONS*2, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Exit", font);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(camera.combined);

        Vector3 mouse= new Vector3();
        mouse.x= Gdx.input.getX();
        mouse.y= Gdx.input.getY();

        camera.unproject(mouse);

        c.render(batch, mouse.x , mouse.y);
        i.render(batch, mouse.x , mouse.y);
        e.render(batch, mouse.x , mouse.y);


    }

    public TextButton getIntructionsbutton() {
        return i;
    }

    public TextButton getContinuebutton() {
        return c;
    }

    public TextButton getExitbutton() {
        return e;
    }



}
