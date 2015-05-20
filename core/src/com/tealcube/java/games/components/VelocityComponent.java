package com.tealcube.java.games.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class VelocityComponent extends Component implements Poolable {

    // float velocities allow us to control the speed of the objects
    // in a much more precise way than ints allow us to do
    private float x = 0.0f;
    private float y = 0.0f;

    @Override
    public void reset() {
        x = 0.0f;
        y = 0.0f;
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
}
