package com.schumakerteam.alpha.gfx;

import java.awt.Image;

/**
 * @author Hudson Schumaker
 */
public final class TileMapTexture {

    private final int mapWidth;
    private final int mapHeight;
    private final Image texture;

    public TileMapTexture(int mapWidth, int mapHeight, Image image) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.texture = image;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public Image getTexture() {
        return texture;
    }
}
