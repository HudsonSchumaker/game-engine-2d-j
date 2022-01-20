package com.schumakerteam.alpha.core;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.component.RigidBodyComponent;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.TransformComponent;

import java.util.HashMap;

public class ComponentTypeIdPoolMap {

    private static Pool<TransformComponent> transformComponentPool = new Pool<>();
    private static Pool<RigidBodyComponent> rigidBodyComponentPool = new Pool<>();
    private static Pool<SpriteComponent> spriteComponentPool = new Pool<>();

    private static final HashMap<Integer, IPool<Component>> COMPONENT_POOL = new HashMap<>();

    static {
        COMPONENT_POOL.put(TransformComponent.COMPONENT_TYPE_ID, transformComponentPool);
        COMPONENT_POOL.put(RigidBodyComponent.COMPONENT_TYPE_ID, rigidBodyComponentPool);
        COMPONENT_POOL.put(SpriteComponent.COMPONENT_TYPE_ID, spriteComponentPool);
    }

    public static IPool<Component> getPoolByComponentTypeId(int typeId) {
        return COMPONENT_POOL.get(typeId);
    }

    public static void setComponentToPool(int entityId, Component c) {
        var pool = ComponentTypeIdPoolMap.getPoolByComponentTypeId(c.getTypeId());
        pool.set(entityId, c);
    }

    public static void removeComponent(int entityId, int typeId) {
        var pool = getPoolByComponentTypeId(typeId);
        pool.remove(entityId);
    }
}
