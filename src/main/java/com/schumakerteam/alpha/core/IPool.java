package com.schumakerteam.alpha.core;

import com.schumakerteam.alpha.ecs.impl.Component;

public interface IPool<C extends Component> {
    void set(int index, Component object);
    C get(int index);
    void remove(int index);
    boolean isEmpty();
    int getSize();
    void clear();
}
