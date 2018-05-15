package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.LinkTrainer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "FrisbeeSim";
		//config.resizable = false;
		config.height = 480;
		config.width = 640;
		new LwjglApplication(new LinkTrainer(), config);
	}
}
