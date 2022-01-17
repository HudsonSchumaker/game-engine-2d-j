package com.schumakerteam.alpha.ecs.impl;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.ecs.IEntity;

public class Entity implements IEntity {

    private final int id;
    private Signature signature;

    public Entity(int id) {
        this.id = id;
        this.signature = new Signature();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void addComponent(Component c) {
        Registry.getInstance().addComponent(this, c);
    }

    public void removeComponent(Entity e, Component c) {
        Registry.getInstance().removeComponent(e, c.getId());
    }

    @Override
    public void setOnSignature(int n) {
        this.signature.set(n);
    }

    @Override
    public void setOffSignature(int n) {
        this.signature.set(n, false);
    }

    @Override
    public boolean getSignature(int n) {
        return this.signature.get(n);
    }
}
