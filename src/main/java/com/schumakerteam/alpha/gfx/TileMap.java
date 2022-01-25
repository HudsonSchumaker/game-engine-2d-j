package com.schumakerteam.alpha.gfx;

import java.awt.image.BufferedImage;

public final class TileMap {

    private final int mapWidth;
    private final int mapHeight;
    private final int tileWidth;
    private final int tileHeight;
    private final BufferedImage bufferedImage;

    public TileMap(int mapWidth, int mapHeight, int tileWidth, int tileHeight, BufferedImage bufferedImage) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.bufferedImage = bufferedImage;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
