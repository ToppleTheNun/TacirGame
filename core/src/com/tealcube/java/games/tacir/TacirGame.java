package com.tealcube.java.games.tacir;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tealcube.games.java.common.events.EventManager;
import com.tealcube.java.games.tacir.entities.EntityManager;
import com.tealcube.java.games.tacir.entities.EntityType;
import com.tealcube.java.games.tacir.systems.LifetimeSystem;
import com.tealcube.java.games.tacir.systems.PhysicsSystem;
import com.tealcube.java.games.tacir.systems.PositionSystem;
import com.tealcube.java.games.tacir.systems.RenderSystem;

import java.util.Random;

public class TacirGame extends Game {

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

    public static final int DESIRED_FPS = 60;

    private static TacirGame instance;

    // Entity/Component System engine, tracks all entities
    private PooledEngine engine;

    // EntitySystems that want to be tracked
    private RenderSystem renderSystem;
    private PhysicsSystem physicsSystem;
    private PositionSystem positionSystem;
    private LifetimeSystem lifetimeSystem;

    // Camera and Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    // Random
    private Random random;

    // Gamestate
    public GameState state;

    // EventManager
    private EventManager eventManager;

    // TextureAtlas
    private TextureAtlas textureAtlas;

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

        // register the Render system
        renderSystem = new RenderSystem(this, camera);
        engine.addSystem(renderSystem);
        physicsSystem = new PhysicsSystem(this);
        engine.addSystem(physicsSystem);
        positionSystem = new PositionSystem();
        engine.addSystem(positionSystem);
        lifetimeSystem = new LifetimeSystem(this);
        engine.addSystem(lifetimeSystem);

        // create our Random with the current time as the seed
        random = new Random(System.currentTimeMillis());

        eventManager = new EventManager();

        textureAtlas = new TextureAtlas(Gdx.files.internal("game.atlas"));

        // create dickbutts in order to test systems
        for (int i = 0; i < 5; i++) {
            createDickbutt(random.nextInt(WORLD_WIDTH - 64), random.nextInt(WORLD_HEIGHT - 64));
        }
        // create box to bounce on
        createBox();
        renderSystem.addedToEngine(engine);
    }

    private void createBox() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));
        Body groundBody = getPhysicsSystem().getWorld().createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(getCamera().viewportWidth, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
    }

    public RenderSystem getRenderSystem() {
        return renderSystem;
    }

    public PhysicsSystem getPhysicsSystem() {
        return physicsSystem;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    private void createDickbutt(int x, int y) {
        Entity dickbutt = EntityManager.spawnEntity(this, EntityType.DICKBUTT, x, y);

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

    public PositionSystem getPositionSystem() {
        return positionSystem;
    }

    @Override
    public void dispose() {
        engine.clearPools();
        renderSystem.dispose();
        physicsSystem.dispose();
        engine.removeSystem(renderSystem);
        engine.removeSystem(lifetimeSystem);
        engine.removeSystem(positionSystem);
        engine.removeSystem(physicsSystem);
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
}
