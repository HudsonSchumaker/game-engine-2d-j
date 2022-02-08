package com.schumakerteam.alpha.gfx;

import java.awt.*;
import java.awt.image.BufferStrategy;

public final class Scene extends Canvas {

    private static final int numberOfBuffers = 2;

    public Scene(int width, int height) {
        this.setIgnoreRepaint(true);
        this.setSize(width, height);
    }

    public void initialize() {
        this.createBufferStrategy(numberOfBuffers);
    }
}
