package com.tealcube.java.games;

import com.badlogic.ashley.core.ComponentMapper;
import com.tealcube.java.games.components.PositionComponent;
import com.tealcube.java.games.components.VelocityComponent;

public final class Mappers {

    // this class implements a sort of bastardized Factory pattern - look it up
    // we do this so that there will only ever be one instance of this class,
    // but without the danger of only having static methods in the class
    private static Mappers instance;

    public static Mappers getInstance() {
        if (instance == null) {
            instance = new Mappers();
        }
        return instance;
    }

    // ComponentMappers allow us to retrieve the components from entities MUCH faster than
    // using the entity's instance method for retrieving components
    private final ComponentMapper<PositionComponent> positionMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;

    // create a private constructor so that only this class is able to
    // construct a copy of itself
    private Mappers() {
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    }

    public ComponentMapper<PositionComponent> getPositionMapper() {
        return positionMapper;
    }

    public ComponentMapper<VelocityComponent> getVelocityMapper() {
        return velocityMapper;
    }
}
