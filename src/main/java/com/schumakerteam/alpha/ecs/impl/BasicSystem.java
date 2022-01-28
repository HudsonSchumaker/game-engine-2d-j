package com.schumakerteam.alpha.ecs.impl;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicSystem {
    private Signature componentSignature;
    private List<Entity> entities;

    public BasicSystem() {
        this.componentSignature = new Signature();
        this.entities = new ArrayList<>();
    }

    protected abstract void update();
    public abstract int getId();
    public abstract int getTypeId();

    public void addEntityToSystem(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntityFromSystem(Entity entity) {
        this.entities.remove(entity);
    }

    public List<Entity> getSystemEntities() {
        return this.entities;
    }

    public void setOnSignature(int n) {
        this.componentSignature.set(n);
    }

    public Signature getComponentSignature() {
        return this.componentSignature;
    }
}
