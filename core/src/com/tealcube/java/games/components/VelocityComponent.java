package com.tealcube.java.games.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public class VelocityComponent extends Component implements Poolable {

    private Vector3 velocity = Vector3.Zero;

    @Override
    public void reset() {
        velocity = Vector3.Zero;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }
}
