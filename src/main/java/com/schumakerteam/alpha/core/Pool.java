package com.schumakerteam.alpha.core;

import com.schumakerteam.alpha.component.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pool<T> implements IPool<Component> {

    private Map<Integer, T> data = new HashMap<>();

    public Pool() {}

    public T getComponent(int index) {
        return this.data.get(index);
    }

    @Override
    public void set(int index, Component object) {
        this.data.put(index, (T)object);
    }

    @Override
    public Component get(int index) {
        return (Component) this.data.get(index);
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
