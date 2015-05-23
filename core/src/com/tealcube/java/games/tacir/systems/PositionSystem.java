package com.tealcube.java.games.tacir.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.tealcube.java.games.tacir.Mappers;
import com.tealcube.java.games.tacir.components.BodyComponent;
import com.tealcube.java.games.tacir.components.SizeComponent;
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
        SizeComponent sizeComponent = Mappers.getInstance().getSizeMapper().get(entity);

        if (bodyComponent != null) {
            transformComponent.getPosition().x = bodyComponent.getBody().getPosition().x;
            transformComponent.getPosition().y = bodyComponent.getBody().getPosition().y;
            if (sizeComponent != null) {
                transformComponent.getPosition().x -= sizeComponent.getWidth() / 2;
                transformComponent.getPosition().y -= sizeComponent.getHeight() / 2;
            }
            transformComponent.setRotation(bodyComponent.getBody().getAngle() * MathUtils.radiansToDegrees);
        }
    }
}
