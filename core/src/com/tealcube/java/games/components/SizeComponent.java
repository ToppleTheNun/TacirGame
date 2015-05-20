package com.tealcube.java.games.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class SizeComponent extends Component implements Poolable {

    private float width = 0.0f;
    private float height = 0.0f;

    @Override
    public void reset() {
        width = 0.0f;
        height = 0.0f;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
