package com.tealcube.java.games.tacir.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tealcube.java.games.tacir.TacirGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
	LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	cfg.title = "tooplesnamesforgamesislames";
	cfg.useGL30 = false;
	cfg.width = 800;
	cfg.height = 450;
	new LwjglApplication(new TacirGame(), cfg);
    }
}
