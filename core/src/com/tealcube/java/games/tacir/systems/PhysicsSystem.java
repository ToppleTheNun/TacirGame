package com.tealcube.java.games.tacir.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.tealcube.java.games.tacir.components.BodyComponent;

public class PhysicsSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private World world;
    private float accumulator;

    // Useful constants for Box2D physics
    public static final float MAX_FRAME_TIME = 0.25f;
    public static final float TIME_STEP = 1 / 45f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    public PhysicsSystem() {
        // create a new Box2D world with a y-gravity of -1
        world = new World(new Vector2(0, -1), true);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BodyComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        physicsStep(deltaTime);
        for (Entity e : entities) {
            // do nothing at the moment
        }
    }

    public World getWorld() {
        return world;
    }

    private void physicsStep(float deltaTime) {
        // implementing a fixed time step
        // max frame time in order to prevent hanging on slow devices
        float frameTime = Math.min(deltaTime, MAX_FRAME_TIME);
        accumulator += frameTime;
        while (accumulator > TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }
}
