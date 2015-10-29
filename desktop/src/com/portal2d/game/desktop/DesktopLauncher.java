package com.portal2d.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.portal2d.game.Portal2D;
import static com.portal2d.game.view.ViewConstants.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = TITLE;
		config.width = VIEWPORT_WIDTH;
		config.height = VIEWPORT_HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Portal2D(), config);
	}
}

