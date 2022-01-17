package com.schumakerteam.alpha.core;

public interface IGame {
    void initialize();
    void setup();
    void processInput();
    void update();
    void render();
    void destroy();
}
