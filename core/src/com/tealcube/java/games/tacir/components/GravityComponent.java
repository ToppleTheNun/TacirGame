package com.tealcube.java.games.tacir.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public class GravityComponent extends Component implements Poolable {

    private Vector3 gravity = Vector3.Zero;

    @Override
    public void reset() {
        gravity = Vector3.Zero;
    }

    public Vector3 getGravity() {
        return gravity;
    }

    public void setGravity(Vector3 gravity) {
        this.gravity = gravity;
    }
}
