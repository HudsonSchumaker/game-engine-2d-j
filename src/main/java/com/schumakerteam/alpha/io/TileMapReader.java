package com.schumakerteam.alpha.io;

import java.io.File;
import java.util.Objects;

public final class TileMapReader {

    public static final

    public void loadTileMap(String fileName) {
        var file = new File(Objects.requireNonNull(this.getClass()
                .getResourceAsStream(path + fileName)));
    }

}
