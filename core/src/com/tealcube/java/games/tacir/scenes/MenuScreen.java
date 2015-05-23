package com.tealcube.java.games.tacir.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tealcube.java.games.tacir.TacirGame;

public class MenuScreen  extends ScreenAdapter {

    TacirGame game;
    OrthographicCamera guiCam;
    Rectangle playButton;
    Rectangle optionsButton;
    Rectangle unlocksButton;
    Rectangle exitButton;
    Vector3 touchPoint;

    public MenuScreen(TacirGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(800, 450);
        guiCam.position.set(800 / 2, 480 / 2, 0);
        playButton = new Rectangle(0, 0, 64, 64);
        optionsButton = new Rectangle(160 - 150, 200 + 18, 300, 36);
        unlocksButton = new Rectangle(160 - 150, 200 - 18, 300, 36);
        exitButton = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
        touchPoint = new Vector3();
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playButton.contains(touchPoint.x, touchPoint.y)) {

            }
            if (unlocksButton.contains(touchPoint.x, touchPoint.y)) {

            }
            if (optionsButton.contains(touchPoint.x, touchPoint.y)) {

            }
            if (exitButton.contains(touchPoint.x, touchPoint.y)) {

            }
        }
    }

    public void draw() {

    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }
}