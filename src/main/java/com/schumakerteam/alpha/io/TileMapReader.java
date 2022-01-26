package com.schumakerteam.alpha.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import static com.schumakerteam.alpha.common.Commons.TILEMAP_PATH;

public final class TileMapReader {

    private static final String COMMA_DELIMITER = ";";

    public void loadTileMap(String fileName) throws IOException {
        var stream = this.getClass().getResourceAsStream(TILEMAP_PATH + fileName);
        assert stream != null;

        var isr = new InputStreamReader(stream, StandardCharsets.UTF_8);
        var buffer = new BufferedReader(isr);
        var line = buffer.readLine();

        while(buffer.readLine() != null) {
            line = buffer.readLine();
            line.split(COMMA_DELIMITER);
        }
    }

    private void setValue(String[] split) {


    }

}
