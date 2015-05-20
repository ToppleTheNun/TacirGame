package com.tealcube.java.games.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tealcube.java.games.Mappers;
import com.tealcube.java.games.TacirGame;
import com.tealcube.java.games.components.SizeComponent;
import com.tealcube.java.games.components.TextureComponent;
import com.tealcube.java.games.components.TransformComponent;

import java.util.Comparator;

public class RenderSystem extends SortedEntitySystem {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    public RenderSystem(OrthographicCamera camera) {
        super(Family.all(SizeComponent.class, TextureComponent.class, TransformComponent.class).get(),
              new ZComparator());
        this.batch = new SpriteBatch();
        this.camera = camera;
    }

    @Override
    public void preUpdate(float deltaTime) {
        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void postUpdate(float deltaTime) {
        batch.end();
    }

    @Override
    public void processEntity(Entity e, float deltaTime) {
        if (e.getId() == TacirGame.INVALID_ENTITY_ID) {
            return;
        }
        TransformComponent transformComponent = Mappers.getInstance().getTransformMapper().get(e);
        TextureComponent textureComponent = Mappers.getInstance().getTextureMapper().get(e);
        SizeComponent sizeComponent = Mappers.getInstance().getSizeMapper().get(e);

        batch.draw(textureComponent.getTextureRegion(), transformComponent.getPosition().x,
                   transformComponent.getPosition().y, sizeComponent.getWidth() * 0.5f,
                   sizeComponent.getHeight() * 0.5f, sizeComponent.getWidth(), sizeComponent.getHeight(),
                   transformComponent.getScale().x, transformComponent.getScale().y,
                   transformComponent.getRotation());
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity a, Entity b) {
            ComponentMapper<TransformComponent> cm = Mappers.getInstance().getTransformMapper();
            TransformComponent aComponent = cm.get(a);
            TransformComponent bComponent = cm.get(b);
            return Float.compare(aComponent.getPosition().z, bComponent.getPosition().z);
        }
    }
}
