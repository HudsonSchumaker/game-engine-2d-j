package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.ecs.impl.Entity;

public interface IRegistry {

    Entity createEntity();
    void addComponent(Entity e, Component c);
    void removeComponent(Entity e, int componentId);
}
