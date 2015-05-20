package com.tealcube.java.games;

import com.badlogic.ashley.core.ComponentMapper;
import com.tealcube.java.games.components.SizeComponent;
import com.tealcube.java.games.components.TransformComponent;
import com.tealcube.java.games.components.TextureComponent;
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
    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<VelocityComponent> velocityMapper;
    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<SizeComponent> sizeMapper;

    // create a private constructor so that only this class is able to
    // construct a copy of itself
    private Mappers() {
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        sizeMapper = ComponentMapper.getFor(SizeComponent.class);
    }

    public ComponentMapper<TransformComponent> getTransformMapper() {
        return transformMapper;
    }

    public ComponentMapper<VelocityComponent> getVelocityMapper() {
        return velocityMapper;
    }

    public ComponentMapper<TextureComponent> getTextureMapper() {
        return textureMapper;
    }

    public ComponentMapper<SizeComponent> getSizeMapper() {
        return sizeMapper;
    }
}
