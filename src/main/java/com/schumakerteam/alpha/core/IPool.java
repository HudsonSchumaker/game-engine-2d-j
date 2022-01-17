package com.schumakerteam.alpha.core;

import com.schumakerteam.alpha.component.Component;

public interface IPool<T extends Component> {
    void set(int index, Component object);
    T get(int index);
    boolean isEmpty();
    int getSize();
    void clear();
}
