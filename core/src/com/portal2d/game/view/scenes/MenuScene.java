package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.ui.ExitButton;
import com.portal2d.game.view.ui.InstructionsButton;
import com.portal2d.game.view.ui.LoadButton;
import com.portal2d.game.view.ui.PlayButton;





import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class MenuScene extends Scene {

    private Texture background;
    private OrthographicCamera camera;
    private PlayButton p;
    private LoadButton l;
    private InstructionsButton i;
    private ExitButton e;

    public MenuScene() {
        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);


        // font settings
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal("core/assets/sprites/fontE.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FORMAT_SIZE; // setting font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        // Creating buttons
        p= new PlayButton(FIRST_BUTTONSTART, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Play", font);
        i=new InstructionsButton(FIRST_BUTTONSTART - SPACE_BETWEEN_BUTTONS*2, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Instructions", font);
        l=new LoadButton(FIRST_BUTTONSTART - SPACE_BETWEEN_BUTTONS, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Load", font);
        e=new ExitButton(FIRST_BUTTONSTART - SPACE_BETWEEN_BUTTONS*3, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Exit", font);


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

        p.render(batch);
        l.render(batch);
        i.render(batch);
        e.render(batch);


    }

    public LoadButton getLoadbutton() {
        return l;
    }

    public InstructionsButton getIntructionsbutton() {
        return i;
    }

    public PlayButton getPlaybutton() {
        return p;
    }

    public ExitButton getExitbutton() {
        return e;
    }

}
