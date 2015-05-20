package com.tealcube.java.games.tacir.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TransformComponent extends Component implements Poolable {

    private Vector3 position = Vector3.Zero;
    private Vector3 scale = new Vector3(1.0f, 1.0f, 1.0f);
    private float rotation = 0.0f;

    @Override
    public void reset() {
        position = Vector3.Zero;
        scale = new Vector3(1.0f, 1.0f, 1.0f);
        rotation = 0.0f;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getScale() {
        return scale;
    }

    public void setScale(Vector3 scale) {
        this.scale = scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
