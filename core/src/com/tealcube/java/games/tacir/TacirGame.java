package com.tealcube.java.games.tacir;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tealcube.java.games.common.events.EventHandler;
import com.tealcube.java.games.common.events.EventManager;
import com.tealcube.java.games.common.events.Listener;
import com.tealcube.java.games.tacir.components.SizeComponent;
import com.tealcube.java.games.tacir.components.TextureComponent;
import com.tealcube.java.games.tacir.components.TransformComponent;
import com.tealcube.java.games.tacir.events.EntityRenderEvent;
import com.tealcube.java.games.tacir.systems.MovementSystem;
import com.tealcube.java.games.tacir.systems.RenderSystem;

import java.util.Random;

public class TacirGame extends ApplicationAdapter {

    // The following four constants define the amount of entities
    // and components that we are able to have in our game
    public static final int ENTITY_POOL_INITIAL_SIZE = 10;
    public static final int ENTITY_POOL_MAX_SIZE = 100;
    public static final int COMPONENT_POOL_INITIAL_SIZE = 10;
    public static final int COMPONENT_POOL_MAX_SIZE = 100;

    // In the pooled entity system, a valid entity will always
    // have an ID that is NOT equal to 0
    public static final long INVALID_ENTITY_ID = 0L;

    // These should be visible to the launchers
    public static final int WORLD_WIDTH = 800;
    public static final int WORLD_HEIGHT = 450;

    private static TacirGame instance;

    // Entity/Component System engine, tracks all entities
    private PooledEngine engine;

    // EntitySystems that want to be tracked
    private MovementSystem movementSystem;
    private RenderSystem renderSystem;

    // Camera and Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    // Random
    private Random random;

    // EventManager
    private EventManager eventManager;

    public TacirGame() {
        instance = this;
    }

    public static TacirGame getInstance() {
        return instance;
    }

    @Override
    public void create() {
        // initialize the ECS engine
        engine = new PooledEngine(ENTITY_POOL_INITIAL_SIZE, ENTITY_POOL_MAX_SIZE, COMPONENT_POOL_INITIAL_SIZE,
                                  COMPONENT_POOL_MAX_SIZE);

        // setup camera here
        camera = new OrthographicCamera();
        viewport = new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.getCamera().position.set(viewport.getCamera().position.x + WORLD_WIDTH * 0.5f,
                                          viewport.getCamera().position.y + WORLD_HEIGHT * 0.5f,
                                          0);
        viewport.getCamera().update();
        viewport.update(WORLD_WIDTH, WORLD_HEIGHT);
        camera.update();

        // register the Movement system
        movementSystem = new MovementSystem();
        engine.addSystem(movementSystem);
        // register the Render system
        renderSystem = new RenderSystem(this, camera);
        engine.addSystem(renderSystem);

        // create our Random with the current time as the seed
        random = new Random(System.currentTimeMillis());

        eventManager = new EventManager();
        eventManager.registerEvents(new Listener() {
            @EventHandler
            public void onEntityRender(EntityRenderEvent event) {
                TransformComponent transformComponent = Mappers.getInstance().getTransformMapper().get(
                        event.getEntity());
                transformComponent.setRotation(transformComponent.getRotation() + 0.1f);
            }
        });

        // create dickbutts in order to test systems
        for (int i = 0; i < 10; i++) {
            createDickbutt(random.nextInt(WORLD_WIDTH - 64), random.nextInt(WORLD_HEIGHT - 64), i);
        }
        renderSystem.addedToEngine(engine);
    }

    private void createDickbutt(int x, int y, int depth) {
        Entity dickbutt = engine.createEntity();
        TextureComponent textureComponent = new TextureComponent();
        SizeComponent sizeComponent = new SizeComponent();
        TransformComponent transformComponent = new TransformComponent();

        // note that this is EXTREMELY inefficient and probably prone to memory leaks
        // texture loading should be done separately and then fed into this system
        textureComponent.setTextureRegion(new TextureRegion(new Texture(Gdx.files.internal("dickbutt.png"))));
        sizeComponent.setWidth(64);
        sizeComponent.setHeight(64);
        transformComponent.setPosition(new Vector3(x, y, depth));
        dickbutt.add(textureComponent);
        dickbutt.add(sizeComponent);
        dickbutt.add(transformComponent);

        engine.addEntity(dickbutt);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        engine.clearPools();
        engine.removeSystem(renderSystem);
        renderSystem.dispose();
        engine.removeSystem(movementSystem);
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Random getRandom() {
        return random;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
