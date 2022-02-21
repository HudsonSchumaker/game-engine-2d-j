package com.schumakerteam.alpha.systems;

import java.awt.*;

public interface Updatable {
    default void update() {}
    default void update(double deltaTime) {}
    default void update(Graphics2D g) {}
}
