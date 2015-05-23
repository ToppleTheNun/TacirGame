package com.tealcube.java.games.tacir;

import com.badlogic.ashley.core.ComponentMapper;
import com.tealcube.java.games.tacir.components.*;

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
    private final ComponentMapper<TextureComponent> textureMapper;
    private final ComponentMapper<SizeComponent> sizeMapper;
    private final ComponentMapper<BodyComponent> bodyMapper;
    private final ComponentMapper<TransformComponent> transformMapper;
    private final ComponentMapper<LifetimeComponent> lifetimeMapper;

    // create a private constructor so that only this class is able to
    // construct a copy of itself
    private Mappers() {
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        sizeMapper = ComponentMapper.getFor(SizeComponent.class);
        bodyMapper = ComponentMapper.getFor(BodyComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        lifetimeMapper = ComponentMapper.getFor(LifetimeComponent.class);
    }

    public ComponentMapper<TextureComponent> getTextureMapper() {
        return textureMapper;
    }

    public ComponentMapper<SizeComponent> getSizeMapper() {
        return sizeMapper;
    }

    public ComponentMapper<BodyComponent> getBodyMapper() {
        return bodyMapper;
    }

    public ComponentMapper<TransformComponent> getTransformMapper() {
        return transformMapper;
    }

    public ComponentMapper<LifetimeComponent> getLifetimeMapper() {
        return lifetimeMapper;
    }
}
