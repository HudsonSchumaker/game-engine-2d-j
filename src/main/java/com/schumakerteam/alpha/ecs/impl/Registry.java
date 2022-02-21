package com.schumakerteam.alpha.ecs.impl;

import com.schumakerteam.alpha.common.ComponentIdMap;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.core.impl.ComponentMap;
import com.schumakerteam.alpha.core.impl.SystemMap;
import com.schumakerteam.alpha.ecs.IRegistry;
import com.schumakerteam.alpha.log.LogService;
import com.schumakerteam.alpha.systems.RenderSystem;

import java.util.*;

import static java.util.Comparator.comparingInt;

public final class Registry implements IRegistry {

    private static final Registry INSTANCE = new Registry();

    private static int numEntities = 0;
    private static int numComponents = 0;
    private static int numSystems = 0;

    private final Set<Entity> entitiesToBeAdded;
    private final Set<Entity> entitiesToBeDestroyed;

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
        for (var entity : entitiesToBeDestroyed) {
            this.removeEntityFromSystem(entity);
            this.removeComponents(entity);
        }
        this.entitiesToBeDestroyed.clear();

        for (var entity : entitiesToBeAdded) {
            this.addEntityToSystems(entity);
        }

        if (entitiesToBeAdded.size() > 0) {
            var renderSystem = SystemMap.getSystem(RenderSystem.SYSTEM_TYPE_ID);
            renderSystem.getSystemEntities().sort(comparingInt(
                    e -> ((SpriteComponent) e.getComponent(SpriteComponent.COMPONENT_TYPE_ID)).getzOrder()
            ));
        }
        this.entitiesToBeAdded.clear();
    }

    @Override
    public Entity createEntity() {
        Entity entity = new Entity(getEntityId());
        this.entitiesToBeAdded.add(entity);
        LogService.getInstance().engine("Entity created with id: " + entity.getId());
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
    public void removeEntityFromSystem(Entity entity) {
        List<BasicSystem> systems = SystemMap.getSystems();
        var entitySignature = entity.getSignature();
        for (var system : systems) {
            if (Signature.contains(entitySignature, system.getComponentSignature())) {
                system.removeEntityFromSystem(entity);
                LogService.getInstance().engine("Entity removed from system: " + system.getClass().getSimpleName());
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
    public void removeComponent(Entity entity, int componentTypeId) {
        if (hasComponentType(entity, componentTypeId)) {
            entity.setOffSignature(componentTypeId);
            var pool = ComponentMap.getPoolByComponentTypeId(componentTypeId);
            var name = pool.get(entity.getId()).getClass().getSimpleName();
            pool.remove(entity.getId());
            LogService.getInstance().engine("Component: " + name + " removed from entity");
        }
    }

    @Override
    public void removeComponents(Entity entity) {
        List<Integer> componentTypeIds = ComponentIdMap.getTypeIds();
        for (var id : componentTypeIds) {
            removeComponent(entity, id);
        }
    }

    @Override
    public boolean hasComponent(Entity entity, int componentTypeId) {
        var pool = ComponentMap.getPoolByComponentTypeId(componentTypeId);
        return pool.get(entity.getId()) != null;
    }

    @Override
    public boolean hasComponentType(Entity entity, int componentTypeId) {
        return entity.testSignature(componentTypeId);
    }

    @Override
    public void destroyEntity(Entity entity) {
        this.entitiesToBeDestroyed.add(entity);
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
