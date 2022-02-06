package com.schumakerteam.alpha.gfx;

import java.awt.Image;

public final class Texture {

    private final int width;
    private final int height;
    private final Image texture;

    public Texture(int width, int height, Image image) {
        this.width = width;
        this.height = height;
        this.texture = image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getTexture() {
        return texture;
    }
}
