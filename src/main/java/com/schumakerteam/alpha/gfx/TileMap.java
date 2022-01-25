package com.schumakerteam.alpha.gfx;

import java.awt.image.BufferedImage;

public final class TileMap {

    private final int mapWidth;
    private final int mapHeight;
    private final int tileWidth;
    private final int tileHeight;
    private BufferedImage bufferedImage;

    public TileMap(int mapWidth, int mapHeight, int tileWidth, int tileHeight, BufferedImage bufferedImage) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.bufferedImage = bufferedImage;
    }
}
