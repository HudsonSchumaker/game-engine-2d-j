package com.schumakerteam.alpha.gfx;

import com.schumakerteam.alpha.common.KeyboardInput;

import java.awt.*;
import java.awt.image.BufferStrategy;

public final class Scene extends Canvas {

    private static final int numberOfBuffers = 2;
    private final KeyboardInput keys;

    public Scene(int width, int height) {
        this.setIgnoreRepaint(true);
        this.setSize(width, height);
        this.keys = new KeyboardInput();
        this.addKeyListener(keys);
    }

    public void initialize() {
        this.createBufferStrategy(numberOfBuffers);
    }

    public KeyboardInput getKeys() {
        return this.keys;
    }
}
