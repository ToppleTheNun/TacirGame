package com.tealcube.java.games.tacir.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tealcube.games.java.common.events.EventException;
import com.tealcube.java.games.tacir.Mappers;
import com.tealcube.java.games.tacir.TacirGame;
import com.tealcube.java.games.tacir.components.SizeComponent;
import com.tealcube.java.games.tacir.components.TextureComponent;
import com.tealcube.java.games.tacir.components.TransformComponent;
import com.tealcube.java.games.tacir.events.EntityRenderEvent;

import java.util.Comparator;

public class RenderSystem extends SortedEntitySystem {

    private final TacirGame game;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public RenderSystem(TacirGame game, OrthographicCamera camera) {
        super(Family.all(SizeComponent.class, TextureComponent.class, TransformComponent.class).get(),
              new ZComparator());
        this.game = game;
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

        EntityRenderEvent entityRenderEvent = new EntityRenderEvent(e);
        try {
            game.getEventManager().callEvent(entityRenderEvent);
        } catch (EventException e1) {
            Gdx.app.log("[DEBUG]", "Could not call EntityRenderEvent for entity " + e.getId());
        }

        if (entityRenderEvent.isCancelled()) {
            return;
        }

        TransformComponent transformComponent = Mappers.getInstance().getTransformMapper().get(e);
        TextureComponent textureComponent = Mappers.getInstance().getTextureMapper().get(e);
        SizeComponent sizeComponent = Mappers.getInstance().getSizeMapper().get(e);

        batch.draw(textureComponent.getTexture(), transformComponent.getPosition().x,
                   transformComponent.getPosition().y, sizeComponent.getWidth() * 0.5f,
                   sizeComponent.getHeight() * 0.5f, sizeComponent.getWidth(), sizeComponent.getHeight(),
                   transformComponent.getScale().x, transformComponent.getScale().y,
                   transformComponent.getRotation());
    }

    @Override
    public void dispose() {
        batch.flush();
        batch.dispose();
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
