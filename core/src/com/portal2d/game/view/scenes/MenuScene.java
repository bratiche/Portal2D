package com.portal2d.game.view.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.portal2d.game.Portal2D;
import com.portal2d.game.view.ui.TextButton;

import java.util.HashSet;
import java.util.Set;

import static com.portal2d.game.view.ViewConstants.*;

/**
 *
 */
public class MenuScene extends Scene {

    private Texture background;
    private OrthographicCamera camera;
    private Set<TextButton> buttons;

    public MenuScene() {
        background = Portal2D.assets.getTexture(TextureName.MENU_BG);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        buttons= new HashSet<TextButton>();

        // font settings
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal("core/assets/sprites/fontE.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = FORMAT_SIZE; // setting font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        // Creating buttons
        buttons.add(new TextButton(FIRST_BUTTONSTART, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Play", font));
        buttons.add(new TextButton(FIRST_BUTTONSTART - SPACE_BETWEEN_BUTTONS*2, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Instructions", font));
        buttons.add(new TextButton(FIRST_BUTTONSTART - SPACE_BETWEEN_BUTTONS, TEXTBUTTON_WIDTH, TEXTBUTTON_HEIGHT, "Options", font));



    }

    public Set<TextButton> getButtons () {
        return buttons;
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

        for( TextButton t : buttons)
            t.render(batch);

    }

}
