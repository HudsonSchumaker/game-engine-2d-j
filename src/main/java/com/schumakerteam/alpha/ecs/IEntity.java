package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.ecs.impl.Signature;

public interface IEntity {
    int getId();
    void addComponent(Component c);
    Component getComponent(int componentTypeId);
    void removeComponent(Component c);
    void removeComponent(int componentTypeId);
    boolean hasComponent(Component c);
    boolean hasComponentType(int componentTypeId);
    void destroy();
    void destroy(long milliseconds);
    Signature getSignature();
    void setOnSignature(int n);
    void setOffSignature(int n);
    boolean testSignature(int n);
}
