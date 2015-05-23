package com.tealcube.java.games.tacir.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.tealcube.java.games.tacir.Mappers;
import com.tealcube.java.games.tacir.TacirGame;
import com.tealcube.java.games.tacir.components.*;

public final class EntityManager {

    // Private constructor to prevent anything actually making this class
    private EntityManager() {
        // do nothing
    }

    public static Entity spawnEntity(TacirGame tacirGame, EntityType entityType, int x, int y) {
        Entity entity = tacirGame.getEngine().createEntity();
        BodyComponent bodyComponent = tacirGame.getEngine().createComponent(BodyComponent.class);
        TextureComponent textureComponent = tacirGame.getEngine().createComponent(TextureComponent.class);
        SizeComponent sizeComponent = tacirGame.getEngine().createComponent(SizeComponent.class);
        TransformComponent transformComponent = tacirGame.getEngine().createComponent(TransformComponent.class);

        transformComponent.setPosition(new Vector2(x, y));

        BodyDef bodyDef;
        FixtureDef fixtureDef;
        Body body;
        Shape shape = null;

        switch (entityType) {
            case BALL:
                textureComponent.setTexture(tacirGame.getTextureAtlas().findRegion("ball"));
                sizeComponent.setWidth(32);
                sizeComponent.setHeight(32);
                bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(new Vector2(x, y));
                body = tacirGame.getPhysicsSystem().getWorld().createBody(bodyDef);
                shape = new CircleShape();
                shape.setRadius(16);
                fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 0.1f;
                fixtureDef.friction = 0.4f;
                fixtureDef.restitution = 1.0f;
                body.createFixture(fixtureDef);
                bodyComponent.setBody(body);
                break;
            case PLAYER:
                textureComponent.setTexture(tacirGame.getTextureAtlas().findRegion("orb"));
                sizeComponent.setWidth(64);
                sizeComponent.setHeight(64);
                bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.KinematicBody;
                bodyDef.position.set(new Vector2(x, y));
                body = tacirGame.getPhysicsSystem().getWorld().createBody(bodyDef);
                shape = new CircleShape();
                shape.setRadius(32);
                fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 0.1f;
                fixtureDef.friction = 0.4f;
                fixtureDef.restitution = 1.0f;
                body.createFixture(fixtureDef);
                body.setGravityScale(0f);
                bodyComponent.setBody(body);
                LifetimeComponent lifetimeComponent = tacirGame.getEngine().createComponent(LifetimeComponent.class);
                lifetimeComponent.setLifetime(1);
                entity.add(lifetimeComponent);
                break;
            case DICKBUTT:
                textureComponent.setTexture(tacirGame.getTextureAtlas().findRegion("dickbutt"));
                sizeComponent.setWidth(64);
                sizeComponent.setHeight(64);
                bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(new Vector2(x, y));
                body = tacirGame.getPhysicsSystem().getWorld().createBody(bodyDef);
                shape = new CircleShape();
                shape.setRadius(32);
                fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 0.1f;
                fixtureDef.friction = 0.4f;
                fixtureDef.restitution = 1.0f;
                body.createFixture(fixtureDef);
                bodyComponent.setBody(body);
                break;
            default:
                break;
        }

        shape.dispose();

        entity.add(bodyComponent);
        entity.add(textureComponent);
        entity.add(sizeComponent);
        entity.add(transformComponent);

        return entity;
    }

    public static void removeEntity(TacirGame game, Entity entity) {
        if (entity.getId() == TacirGame.INVALID_ENTITY_ID) {
            return;
        }
        entity.remove(SizeComponent.class);
        entity.remove(TextureComponent.class);
        entity.remove(TransformComponent.class);
        if (Mappers.getInstance().getBodyMapper().has(entity)) {
            BodyComponent bodyComponent = Mappers.getInstance().getBodyMapper().get(entity);
            if (bodyComponent.getBody() != null) {
                Array<Fixture> fixtureArray = new Array<Fixture>(bodyComponent.getBody().getFixtureList());
                for (Fixture fixture : fixtureArray) {
                    bodyComponent.getBody().destroyFixture(fixture);
                }
            }
        }
        entity.remove(BodyComponent.class);
        entity.remove(LifetimeComponent.class);
        game.getEngine().removeEntity(entity);
    }
}
