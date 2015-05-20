package com.tealcube.java.games.tacir.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Comparator;

public abstract class SortedEntitySystem extends EntitySystem implements EntityListener, Disposable {

    private Family family;
    private Array<Entity> sortedEntities;
    private final ImmutableArray<Entity> entities;
    private boolean shouldSort;
    private Comparator<Entity> comparator;

    public SortedEntitySystem(Family family, Comparator<Entity> comparator) {
        this(family, comparator, 0);
    }

    public SortedEntitySystem(Family family, Comparator<Entity> comparator, int priority) {
        super(priority);

        this.family = family;
        this.comparator = comparator;
        this.sortedEntities = new Array<Entity>(false, 16);
        this.entities = new ImmutableArray<Entity>(sortedEntities);
    }

    public void forceSort() {
        shouldSort = true;
    }

    private void sort() {
        if (shouldSort) {
            sortedEntities.sort(comparator);
            shouldSort = false;
        }
    }

    @Override
    public void addedToEngine(Engine engine) {
        ImmutableArray<Entity> newEntities = engine.getEntitiesFor(family);
        sortedEntities.clear();
        if (newEntities.size() > 0) {
            for (int i = 0; i < newEntities.size(); ++i) {
                sortedEntities.add(newEntities.get(i));
            }
            sortedEntities.sort(comparator);
        }
        shouldSort = false;
        engine.addEntityListener(family, this);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        engine.removeEntityListener(this);
        sortedEntities.clear();
        shouldSort = false;
    }

    @Override
    public void entityAdded(Entity entity) {
        sortedEntities.add(entity);
        shouldSort = true;
    }

    @Override
    public void entityRemoved(Entity entity) {
        sortedEntities.removeValue(entity, true);
        shouldSort = true;
    }

    public void preUpdate(float deltaTime) {
        // do nothing by default
    }

    @Override
    public void update(float deltaTime) {
        preUpdate(deltaTime);
        sort();
        for (int i = 0; i < sortedEntities.size; ++i) {
            processEntity(sortedEntities.get(i), deltaTime);
        }
        postUpdate(deltaTime);
    }

    public void postUpdate(float deltaTime) {
        // do nothing by default
    }

    public ImmutableArray<Entity> getEntities() {
        sort();
        return entities;
    }

    public Family getFamily() {
        return family;
    }

    @Override
    public void dispose() {
        // do nothing by default
    }

    protected abstract void processEntity (Entity entity, float deltaTime);

}
