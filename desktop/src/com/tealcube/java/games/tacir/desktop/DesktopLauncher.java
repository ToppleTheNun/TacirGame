package com.tealcube.java.games.tacir.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.tealcube.java.games.tacir.TacirGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "tooplesnamesforgamesislames";
        cfg.useGL30 = false;
        cfg.width = TacirGame.WORLD_WIDTH;
        cfg.height = TacirGame.WORLD_HEIGHT;

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 512;
        settings.maxHeight = 512;
        TexturePacker.process(settings, "../../textures", ".", "game");

        new LwjglApplication(new TacirGame(), cfg);
    }
}
