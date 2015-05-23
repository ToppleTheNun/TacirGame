package com.tealcube.java.games.tacir.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.tealcube.java.games.tacir.Mappers;
import com.tealcube.java.games.tacir.TacirGame;
import com.tealcube.java.games.tacir.components.LifetimeComponent;
import com.tealcube.java.games.tacir.entities.EntityManager;

public class LifetimeSystem extends IteratingSystem {

    private TacirGame game;

    public LifetimeSystem(TacirGame game) {
        super(Family.all(LifetimeComponent.class).get());
        this.game = game;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (entity.getId() == TacirGame.INVALID_ENTITY_ID) {
            return;
        }
        LifetimeComponent lifetimeComponent = Mappers.getInstance().getLifetimeMapper().get(entity);
        long currentTime = System.currentTimeMillis();
        long createdTime = lifetimeComponent.getCreated();
        long diff = currentTime - createdTime;
        if ((diff / 1000) >= lifetimeComponent.getLifetime()) {
            EntityManager.removeEntity(game, entity);
        }
    }

}
