package com.schumakerteam.alpha.ecs;

import com.schumakerteam.alpha.component.Component;

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
        this.setSignature(c.getTypeId());
    }

    @Override
    public void setSignature(int n) {
        this.signature.set(n);
    }

    @Override
    public boolean getSignature(int n) {
        return this.signature.get(n);
    }
}
