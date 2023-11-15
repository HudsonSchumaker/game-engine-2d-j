package com.schumakerteam.alpha.ecs.impl;

import com.schumakerteam.alpha.systems.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hudson Schumaker
 */
public abstract class BasicSystem implements Updatable {
    private final Signature componentSignature;
    private final List<Entity> entities;

    protected BasicSystem() {
        this.componentSignature = new Signature();
        this.entities = new ArrayList<>();
    }

    protected abstract void setOnSignatures();
    public abstract int getId();
    public abstract int getTypeId();

    public void addEntityToSystem(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntityFromSystem (Entity entity) {
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
