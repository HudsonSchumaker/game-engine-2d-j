package com.schumakerteam.alpha.ecs.impl;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.core.impl.ComponentMap;
import com.schumakerteam.alpha.core.impl.SystemMap;
import com.schumakerteam.alpha.ecs.IRegistry;
import com.schumakerteam.alpha.log.LogService;

import java.util.*;

public final class Registry implements IRegistry {

    private static final Registry INSTANCE = new Registry();

    private static int numEntities = 0;
    private static int numComponents = 0;
    private static int numSystems = 0;

    private Set<Entity> entitiesToBeAdded;
    private Set<Entity> entitiesToBeDestroyed;

    private Registry() {
        this.entitiesToBeAdded = new HashSet<>();
        this.entitiesToBeDestroyed = new HashSet<>();
    }

    public static Registry getInstance() {
        return INSTANCE;
    }

    public int getEntityId() {
        return numEntities++;
    }

    public int getComponentId() {
        return numComponents++;
    }

    public int getSystemId() {
        return numSystems++;
    }

    @Override
    public void update() {

    }

    @Override
    public Entity createEntity() {
        int entityId = getEntityId();
        Entity entity = new Entity(entityId);
        this.entitiesToBeAdded.add(entity);
        LogService.getInstance().engine("Entity created with id: " + entityId);
        return entity;
    }

    @Override
    public void addEntityToSystems(Entity entity) {
        List<BasicSystem> systems = SystemMap.getSystems();
        var entitySignature = entity.getSignature();
        for (var system : systems) {
            if (Signature.contains(entitySignature, system.getComponentSignature())) {
                system.addEntityToSystem(entity);
                LogService.getInstance().engine("Entity with id: " + entity.getId() + " added to system: " + system.getClass().getSimpleName());
            }
        }
    }

    @Override
    public void addComponent(Entity entity, Component c) {
        int entityId = entity.getId();
        ComponentMap.setComponentToPool(entityId, c);
        entity.setOnSignature(c.getTypeId());
    }

    @Override
    public Component getComponent(Entity entity, int componentTypeId) {
        var pool = ComponentMap.getPoolByComponentTypeId(componentTypeId);
        return pool.get(entity.getId());
    }

    @Override
    public void removeComponent(Entity entity, int componentId) {
        entity.setOffSignature(componentId);
    }

    @Override
    public boolean hasComponent(Entity entity, int componentId) {
        return entity.testSignature(componentId);
    }

    @Override
    public boolean hasComponentType(Entity e, int componentTypeId) {
        var pool = ComponentMap.getPoolByComponentTypeId(componentTypeId);
        return pool.get(e.getId()) != null;
    }

    @Override
    public void addSystem(BasicSystem system) {
        SystemMap.setSystem(system.getTypeId(), system);
    }

    @Override
    public BasicSystem getSystem(int systemTypeId) {
        return SystemMap.getSystem(systemTypeId);
    }

    @Override
    public void removeSystem(BasicSystem system) {
        //TODO
    }
}
