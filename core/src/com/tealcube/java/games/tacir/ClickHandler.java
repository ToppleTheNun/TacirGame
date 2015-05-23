package com.tealcube.java.games.tacir;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tealcube.java.games.tacir.entities.EntityManager;
import com.tealcube.java.games.tacir.entities.EntityType;

public class ClickHandler {

    OrthographicCamera guiCam;
    Rectangle soundBounds;
    Rectangle playBounds;
    Rectangle highscoresBounds;
    Rectangle helpBounds;
    Vector3 touchPoint;

    public ClickHandler () {
        soundBounds = new Rectangle(0, 0, 64, 64);
        playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
        highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
        helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
        touchPoint = new Vector3();
    }

    public void gameplayClick() {
        touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        Entity entity = EntityManager.spawnEntity(TacirGame.getInstance(), EntityType.PLAYER, (int) touchPoint.x, (int) touchPoint.y);
        TacirGame.getInstance().getEngine().addEntity(entity);
        }
    }