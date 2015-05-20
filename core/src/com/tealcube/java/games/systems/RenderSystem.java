package com.tealcube.java.games.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tealcube.java.games.Mappers;
import com.tealcube.java.games.components.SizeComponent;
import com.tealcube.java.games.components.TextureComponent;
import com.tealcube.java.games.components.TransformComponent;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    public RenderSystem(OrthographicCamera camera) {
        this.batch = new SpriteBatch();
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, TextureComponent.class,
                                                    SizeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        for (Entity e : entities) {
            TransformComponent transformComponent = Mappers.getInstance().getTransformMapper().get(e);
            TextureComponent textureComponent = Mappers.getInstance().getTextureMapper().get(e);
            SizeComponent sizeComponent = Mappers.getInstance().getSizeMapper().get(e);

            batch.draw(textureComponent.getTextureRegion(), transformComponent.getPosition().x,
                       transformComponent.getPosition().y, sizeComponent.getWidth() * 0.5f,
                       sizeComponent.getHeight() * 0.5f, sizeComponent.getWidth(), sizeComponent.getHeight(),
                       transformComponent.getScale().x, transformComponent.getScale().y, transformComponent.getRotation());
        }

        batch.end();
    }

}
