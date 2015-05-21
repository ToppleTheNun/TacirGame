package com.tealcube.java.games.tacir.events;

import com.badlogic.ashley.core.Entity;

public class EntityRenderEvent extends EntityEvent {
    public EntityRenderEvent(Entity entity) {
        super(entity);
    }
}
