package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;

public interface IEntity {
    int getId();
    void addComponent(Component c);
    void setOnSignature(int n);
    void setOffSignature(int n);
    boolean getSignature(int n);

}
