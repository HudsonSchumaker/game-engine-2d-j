package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.core.IPool;
import com.schumakerteam.alpha.component.*;
import com.schumakerteam.alpha.log.LogService;

import java.util.HashMap;

public final class ComponentMap {

    private static final Pool<TransformComponent> transformComponentPool = new Pool<>();
    private static final Pool<RigidBodyComponent> rigidBodyComponentPool = new Pool<>();
    private static final Pool<SpriteComponent> spriteComponentPool = new Pool<>();
    private static final Pool<TileMapComponent> tileMapComponentPool = new Pool<>();

    private static final HashMap<Integer, IPool<Component>> COMPONENT_POOL = new HashMap<>();

    static {
        COMPONENT_POOL.put(TransformComponent.COMPONENT_TYPE_ID, transformComponentPool);
        COMPONENT_POOL.put(RigidBodyComponent.COMPONENT_TYPE_ID, rigidBodyComponentPool);
        COMPONENT_POOL.put(SpriteComponent.COMPONENT_TYPE_ID, spriteComponentPool);
        COMPONENT_POOL.put(TileMapComponent.COMPONENT_TYPE_ID, tileMapComponentPool);
    }

    public ComponentMap() {
        LogService.getInstance().engine("ComponentMap created.");
    }

    public static IPool<Component> getPoolByComponentTypeId(int typeId) {
        return COMPONENT_POOL.get(typeId);
    }

    public static void setComponentToPool(int entityId, Component c) {
        var pool = ComponentMap.getPoolByComponentTypeId(c.getTypeId());
        pool.set(entityId, c);
    }

    public static void removeComponent(int entityId, int typeId) {
        var pool = ComponentMap.getPoolByComponentTypeId(typeId);
        pool.remove(entityId);
    }
}
