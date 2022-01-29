package com.schumakerteam.alpha.gfx;

import java.awt.image.BufferedImage;

public final class TileMapTexture {

    private final int mapWidth;
    private final int mapHeight;
    private final BufferedImage bufferedImage;

    public TileMapTexture(int mapWidth, int mapHeight, BufferedImage bufferedImage) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.bufferedImage = bufferedImage;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
