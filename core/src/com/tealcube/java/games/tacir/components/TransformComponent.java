package com.tealcube.java.games.tacir.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TransformComponent extends Component implements Poolable {

    private Vector2 position = Vector2.Zero;
    private Vector2 scale = new Vector2(1f, 1f);
    private float rotation = 0f;

    @Override
    public void reset() {
        position = Vector2.Zero;
        scale = new Vector2(1f, 1f);
        rotation = 0f;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
