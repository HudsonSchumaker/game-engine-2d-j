package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.core.ComponentTypeIdPoolMap;
import com.schumakerteam.alpha.core.IPool;
import com.schumakerteam.alpha.log.LogService;

import java.util.*;

public class Registry {

    private static final Registry INSTANCE = new Registry();

    private static int numEntities = 0;
    private static int numComponents = 0;

    private Map<String, BasicSystem> systems;

    private Set<Entity> entitiesToBeAdded;
    private Set<Entity> entitiesToBeDestroyed;

    private Registry() {
        this.systems = new HashMap<>();
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

    public Entity createEntity() {
        int entityId = getEntityId();
        Entity entity = new Entity(entityId);
        this.entitiesToBeAdded.add(entity);
        LogService.getInstance().engine("Entity created with id: " + entityId);
        return entity;
    }

    public void addComponent(Entity e, Component c) {
        int entityId = e.getId();
        ComponentTypeIdPoolMap.setComponentToPool(entityId, c);

        //entityComponentSignatures.set();
    }




}
