package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;

public class Entity {

    private final int id;
    private Signature signature;

    public Entity(int id) {
        this.id = id;
        this.signature = new Signature();
    }

    public int getId() {
        return this.id;
    }

    public void addComponent(Component c) {
        Registry.getInstance().addComponent(this, c);
        set(c.getTypeId());
    }

    public void set(int n) {
        this.signature.set(n);
    }

    public boolean get(int n) {
        return this.signature.get(n);
    }
}
