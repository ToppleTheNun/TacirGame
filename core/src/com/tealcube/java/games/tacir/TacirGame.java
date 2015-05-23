package com.tealcube.java.games.tacir;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tealcube.games.java.common.events.EventManager;
import com.tealcube.java.games.tacir.components.SizeComponent;
import com.tealcube.java.games.tacir.components.TextureComponent;
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

    // Useful constants for Box2D physics
    public static final float MAX_FRAME_TIME = 0.25f;
    public static final float TIME_STEP = 1 / 45f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    public static final int DESIRED_FPS = 60;

    private static TacirGame instance;

    // Entity/Component System engine, tracks all entities
    private PooledEngine engine;

    // EntitySystems that want to be tracked
    private RenderSystem renderSystem;

    // Camera and Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    // Random
    private Random random;

    // EventManager
    private EventManager eventManager;

    // TextureAtlas
    private TextureAtlas textureAtlas;

    // Box2D world
    private World world;

    public TacirGame() {
        instance = this;
    }

    public static TacirGame getInstance() {
        return instance;
    }

    @Override
    public void create() {
        // initialize Box2D
        Box2D.init();

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

        // create the Box2D world with no gravity (we want to control it ourselves)
        world = new World(Vector2.Zero, true);

        // register the Render system
        renderSystem = new RenderSystem(this, camera);
        engine.addSystem(renderSystem);

        // create our Random with the current time as the seed
        random = new Random(System.currentTimeMillis());

        eventManager = new EventManager();

        textureAtlas = new TextureAtlas(Gdx.files.internal("game.atlas"));

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

        // note that this is EXTREMELY inefficient and probably prone to memory leaks
        // texture loading should be done separately and then fed into this system
        textureComponent.setTexture(textureAtlas.findRegion("dickbutt"));
        sizeComponent.setWidth(64);
        sizeComponent.setHeight(64);

        dickbutt.add(textureComponent);
        dickbutt.add(sizeComponent);

        engine.addEntity(dickbutt);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // creating our own definition of deltaTime so that our updates
        // are not tied to libGDX's definition
        int actualFPS = Gdx.graphics.getFramesPerSecond();
        actualFPS = (actualFPS == 0) ? 3000 : actualFPS;
        float time = ((float) DESIRED_FPS) / actualFPS;
        engine.update(time);
    }

    @Override
    public void dispose() {
        engine.clearPools();
        engine.removeSystem(renderSystem);
        renderSystem.dispose();
        textureAtlas.dispose();
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

    public World getWorld() {
        return world;
    }
}
