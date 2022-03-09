package com.schumakerteam.alpha.ecs.impl;

import com.schumakerteam.alpha.ecs.IEntity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Hudson Schumaker
 */
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
       this.removeComponent(c.getTypeId());
    }

    @Override
    public void removeComponent(int componentTypeId) {
        Registry.getInstance().removeComponent(this, componentTypeId);
    }

    @Override
    public boolean hasComponent(Component c) {
        return Registry.getInstance().hasComponent(this, c.getId());
    }

    @Override
    public boolean hasComponentType(int componentTypeId) {
        return this.testSignature(componentTypeId);
    }

    @Override
    public void destroy() {
        Registry.getInstance().destroyEntity(this);
    }

    @Override
    public void destroy(long milliseconds) {
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            public void run() {
                destroy();
            }
        };
        timer.schedule(task,  milliseconds);
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

    @Override
    public String toString() {
        return "Entity{" + "id=" + id + ", signature=" + signature + '}';
    }
}
