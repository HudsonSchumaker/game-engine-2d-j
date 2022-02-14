package com.schumakerteam.alpha.core;

public interface IGame extends Runnable {
    void initialize();
    void setup();
    void update(double deltaTime);
    void processInput();
    void render();
}
