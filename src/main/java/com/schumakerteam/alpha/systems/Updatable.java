package com.schumakerteam.alpha.systems;

import java.awt.*;

/**
 * @author Hudson Schumaker
 */
public interface Updatable {
    default void update() {}
    default void update(double deltaTime) {}
    default void update(Graphics2D g) {}
}
