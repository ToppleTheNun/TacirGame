package com.tealcube.java.games.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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
        TransformComponent position = Mappers.getInstance().getTransformMapper().get(entity);
        VelocityComponent velocity = Mappers.getInstance().getVelocityMapper().get(entity);

        position.setX(position.getX() + velocity.getX() * deltaTime);
        position.setY(position.getY() + velocity.getY() * deltaTime);
    }

}
