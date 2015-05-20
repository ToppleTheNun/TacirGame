package com.tealcube.java.games.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.tealcube.java.games.Mappers;
import com.tealcube.java.games.TacirGame;
import com.tealcube.java.games.components.TransformComponent;
import com.tealcube.java.games.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        // check if the entity is valid
        if (entity.getId() == TacirGame.INVALID_ENTITY_ID) {
            return;
        }

        // below is faster than "entity.getComponent(TransformComponent.class)" or whatever
        // the entity equivalent is, but the source code is dumb
        TransformComponent positionComponent = Mappers.getInstance().getTransformMapper().get(entity);
        VelocityComponent velocityComponent = Mappers.getInstance().getVelocityMapper().get(entity);

        Vector2 velocity = velocityComponent.getVelocity();

        Vector2 oldPosition = positionComponent.getPosition();
        Vector2 newPosition = new Vector2(oldPosition.x + velocity.x * deltaTime,
                                          oldPosition.y + velocity.y * deltaTime);
        positionComponent.setPosition(newPosition);
    }

}
