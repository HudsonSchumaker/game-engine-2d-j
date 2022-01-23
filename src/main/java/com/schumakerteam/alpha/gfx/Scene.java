package com.schumakerteam.alpha.gfx;

import java.awt.*;
import java.awt.image.BufferStrategy;

public final class Scene extends Canvas {

    public Scene(int width, int height) {
        this.setSize(width, height);
    }

    public void initialize() {
        this.createBufferStrategy(2);
    }
}
