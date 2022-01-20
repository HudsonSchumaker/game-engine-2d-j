package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Entity;

public interface IRegistry {

    void update();
    Entity createEntity();
    void addEntityToSystems(Entity entity);
    void addComponent(Entity e, Component c);
    Component getComponent(Entity entity, int componentTypeId);
    void removeComponent(Entity e, int componentId);
    boolean hasComponent(Entity e, int componentId);
    boolean hasComponentType(Entity e, int componentTypeId);
    void addSystem(BasicSystem system);
    BasicSystem getSystem(int componentId);
    void removeSystem(BasicSystem system);
}
