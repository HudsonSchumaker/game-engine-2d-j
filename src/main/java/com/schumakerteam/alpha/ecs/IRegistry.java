package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.ecs.impl.Component;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Entity;

/**
 * @author Hudson Schumaker
 */
public interface IRegistry {
    void update();
    Entity createEntity();
    void addEntityToSystems(Entity e);
    void removeEntityFromSystem(Entity e);
    void addComponent(Entity e, Component c);
    void removeComponents(Entity e);
    Component getComponent(Entity entity, int componentTypeId);
    void removeComponent(Entity e, int componentId);
    boolean hasComponent(Entity e, int componentId);
    boolean hasComponentType(Entity e, int componentTypeId);
    void destroyEntity(Entity e);
    void addSystem(BasicSystem system);
    BasicSystem getSystem(int systemTypeId);
    void removeSystem(BasicSystem system);
}
