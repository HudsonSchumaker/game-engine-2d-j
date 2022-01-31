package com.schumakerteam.alpha.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class TileMapTexture {

    private final int mapWidth;
    private final int mapHeight;
    private final Image bufferedImage;

    public TileMapTexture(int mapWidth, int mapHeight, Image bufferedImage) {
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

    public Image getBufferedImage() {
        return bufferedImage;
    }
}
