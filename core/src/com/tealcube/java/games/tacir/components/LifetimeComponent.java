package com.tealcube.java.games.tacir.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class LifetimeComponent extends Component implements Poolable {

    private long created;
    private int lifetime = 0;

    public LifetimeComponent() {
        created = System.currentTimeMillis();
    }

    @Override
    public void reset() {
        created = System.currentTimeMillis();
        lifetime = 0;
    }

    public long getCreated() {
        return created;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}
