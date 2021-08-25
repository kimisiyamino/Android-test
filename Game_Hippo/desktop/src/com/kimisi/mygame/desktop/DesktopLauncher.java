package com.kimisi.mygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kimisi.mygame.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "GAME1";
		config.width = 800;
		config.height = 480;
		//config.foregroundFPS = 60;
		//config.backgroundFPS = 60;
		config.vSyncEnabled = false;
		config.resizable = true;

		new LwjglApplication(new MyGame(), config);
	}
}
