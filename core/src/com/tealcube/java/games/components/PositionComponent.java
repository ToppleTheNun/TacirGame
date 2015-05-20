package com.tealcube.java.games.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PositionComponent extends Component implements Poolable {

    // Making our x and y coordinates floats allows us to be
    // more precise than if we used ints with the way that
    // our movement code will work
    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;

    @Override
    public void reset() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
