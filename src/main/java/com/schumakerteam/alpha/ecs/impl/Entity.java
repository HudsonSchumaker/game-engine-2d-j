package com.schumakerteam.alpha.ecs.impl;

import com.schumakerteam.alpha.component.Component;
import com.schumakerteam.alpha.core.impl.ComponentMap;
import com.schumakerteam.alpha.ecs.IEntity;

public final class Entity implements IEntity {

    private final int id;
    private final Signature signature;

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

    @Override
    public Component getComponent(int componentTypeId) {
        return Registry.getInstance().getComponent(this, componentTypeId);
    }

    @Override
    public void removeComponent(Component c) {
        Registry.getInstance().removeComponent(this, c.getTypeId());
    }

    @Override
    public void removeComponent(int componentTypeId) {
        if (hasComponentType(componentTypeId)) {
            var pool = ComponentMap.getPoolByComponentTypeId(componentTypeId);
            var component = pool.get(this.id);
            this.removeComponent(component);
        }
    }

    @Override
    public boolean hasComponent(Component c) {
        return Registry.getInstance().hasComponent(this, c.getId());
    }

    @Override
    public boolean hasComponentType(int componentTypeId) {
        return Registry.getInstance().hasComponentType(this, componentTypeId);
    }

    @Override
    public Signature getSignature() {
        return this.signature;
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
    public boolean testSignature(int n) {
        return this.signature.get(n);
    }
}
