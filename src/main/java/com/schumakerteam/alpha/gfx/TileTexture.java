package com.schumakerteam.alpha.gfx;

import java.awt.Image;

public final class TileTexture {

    private final Image texture;

    public TileTexture(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return this.texture;
    }
}
