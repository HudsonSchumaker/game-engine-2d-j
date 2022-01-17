package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;

public interface IEntity {
    int getId();
    void addComponent(Component c);
    void setSignature(int n);
    boolean getSignature(int n);
}
