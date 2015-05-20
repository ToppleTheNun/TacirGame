package com.tealcube.java.games.tacir.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;
import com.tealcube.java.games.tacir.Mappers;
import com.tealcube.java.games.tacir.TacirGame;
import com.tealcube.java.games.tacir.components.TransformComponent;
import com.tealcube.java.games.tacir.components.VelocityComponent;

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

        Vector3 velocity = velocityComponent.getVelocity();

        Vector3 oldPosition = positionComponent.getPosition();
        Vector3 newPosition = new Vector3(oldPosition.x + velocity.x * deltaTime,
                                          oldPosition.y + velocity.y * deltaTime,
                                          oldPosition.z + velocity.z * deltaTime);
        positionComponent.setPosition(newPosition);
    }

}
