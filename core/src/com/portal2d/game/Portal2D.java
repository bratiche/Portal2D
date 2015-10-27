package com.portal2d.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.portal2d.game.controller.Assets;
import com.portal2d.game.controller.GameStateManager;
import com.portal2d.game.controller.states.PlayState;

public class Portal2D extends ApplicationAdapter {

	private SpriteBatch batch;
	private GameStateManager gsm;
	public static Assets assets;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assets = new Assets();

		assets.loadTextures();
		assets.loadTiledMaps();

		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm));
	}

	@Override
	public void render () {

		//updating
		gsm.handleInput();
		gsm.update(Gdx.graphics.getDeltaTime());

		//rendering
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.render(batch);

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
