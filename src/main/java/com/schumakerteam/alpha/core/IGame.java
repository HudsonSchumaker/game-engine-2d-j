package com.schumakerteam.alpha.core;

import com.schumakerteam.alpha.events.EventListener;

/**
 * @author Hudson Schumaker
 */
public interface IGame extends Runnable, EventListener {
    void initialize();
    void setup();
    void update(double deltaTime);
    void processInput();
    void render();
}
