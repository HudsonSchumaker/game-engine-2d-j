package com.schumakerteam.alpha.ecs.impl;

import java.util.ArrayList;
import java.util.List;

public class BasicSystem {
    private Signature componentSignature;
    private List<Entity> entities;

    public BasicSystem() {
        this.componentSignature = new Signature();
        this.entities = new ArrayList<>();
    }

    public void addEntityToSystem(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntityFromSystem(Entity entity) {
        this.entities.remove(entity);
    }

    public List<Entity> getSystemEntities() {
        return this.entities;
    }

    public Signature getComponentSignature() {
       return this.componentSignature;
    }
}
