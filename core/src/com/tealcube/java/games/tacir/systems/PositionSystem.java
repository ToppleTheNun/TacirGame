package com.tealcube.java.games.tacir.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.tealcube.java.games.tacir.Mappers;
import com.tealcube.java.games.tacir.components.BodyComponent;
import com.tealcube.java.games.tacir.components.TransformComponent;

// This system is just for making our positions equivalent across libraries
public class PositionSystem extends IteratingSystem {
    public PositionSystem() {
        super(Family.all(TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = Mappers.getInstance().getTransformMapper().get(entity);
        BodyComponent bodyComponent = Mappers.getInstance().getBodyMapper().get(entity);

        if (bodyComponent != null) {
            transformComponent.getPosition().x = bodyComponent.getBody().getPosition().x * PhysicsSystem.METERS_TO_PIXELS;
            transformComponent.getPosition().y = bodyComponent.getBody().getPosition().y * PhysicsSystem.METERS_TO_PIXELS;
        }
    }
}
