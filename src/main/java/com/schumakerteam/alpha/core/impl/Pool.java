package com.schumakerteam.alpha.core.impl;

import com.schumakerteam.alpha.core.IPool;
import com.schumakerteam.alpha.ecs.impl.Component;

import java.util.HashMap;
import java.util.Map;

public final class Pool<C> implements IPool<Component> {

    private final Map<Integer, C> data = new HashMap<>();

    @Override
    public void set(int index, Component object) {
        this.data.put(index, (C) object);
    }

    @Override
    public Component get(int index) {
        return (Component) this.data.getOrDefault(index, null);
    }

    @Override
    public void remove(int index) {
        this.data.remove(index);
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public int getSize() {
        return this.data.size();
    }

    @Override
    public void clear() {
        this.data.clear();
    }
}
