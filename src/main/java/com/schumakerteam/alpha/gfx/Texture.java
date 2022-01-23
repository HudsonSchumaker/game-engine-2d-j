package com.schumakerteam.alpha.gfx;

import java.awt.image.BufferedImage;

public final class Texture {

    private final int width;
    private final int height;
    private BufferedImage bufferedImage;

    public Texture(int width, int height, BufferedImage bufferedImage) {
        this.width = width;
        this.height = height;
        this.bufferedImage = bufferedImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
