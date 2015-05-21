package com.tealcube.java.games.tacir.events;

import com.badlogic.ashley.core.Entity;

public class EntityEvent extends TacirCancellableEvent {

    private final Entity entity;

    public EntityEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
