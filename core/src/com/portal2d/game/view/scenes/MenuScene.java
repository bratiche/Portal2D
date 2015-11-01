package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.ui.*;


import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class MenuScene extends Scene {

    private Texture background;
    private OrthographicCamera camera;
    private TextButton p;
    private TextButton l;
    private TextButton i;
    private TextButton e;

    public MenuScene() {
        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);


        // font settings
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal("core/assets/font/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FORMAT_SIZE; // setting font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        // Creating buttons
        p= new TextButton(FIRST_BUTTONSTART_MENU, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Play", font);
        l=new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Load", font);
        i=new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS*2, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Instructions", font);
        e=new TextButton(FIRST_BUTTONSTART_MENU - SPACE_BETWEEN_BUTTONS*3, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Exit", font);


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

        Vector3 mouse= new Vector3();
        mouse.x= Gdx.input.getX();
        mouse.y= Gdx.input.getY();

        camera.unproject(mouse);

        p.render(batch, mouse.x , mouse.y);
        l.render(batch, mouse.x , mouse.y);
        i.render(batch, mouse.x , mouse.y);
        e.render(batch, mouse.x , mouse.y);


    }

    public TextButton getLoadbutton() {
        return l;
    }

    public TextButton getIntructionsbutton() {
        return i;
    }

    public TextButton getPlaybutton() {
        return p;
    }

    public TextButton getExitbutton() {
        return e;
    }

}
