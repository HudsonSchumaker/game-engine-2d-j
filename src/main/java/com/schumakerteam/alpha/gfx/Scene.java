package com.schumakerteam.alpha.gfx;

import java.awt.*;
import java.awt.image.BufferStrategy;

public final class Scene extends Canvas {

    private BufferStrategy strategy;

    public Scene(int width, int height) {
        setSize(width, height);
    }

    public void initialize() {
        this.createBufferStrategy(3);
        this.strategy = getBufferStrategy();
    }
}
