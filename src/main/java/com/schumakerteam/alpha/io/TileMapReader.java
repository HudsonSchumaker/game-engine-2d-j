package com.schumakerteam.alpha.io;

import com.schumakerteam.alpha.component.TileComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.core.impl.AssetManager;
import com.schumakerteam.alpha.geometry.Scale2D;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.gfx.TileTexture;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.schumakerteam.alpha.common.Commons.TILEMAP_PATH;

public final class TileMapReader {

    private static final String COMMA_DELIMITER = ",";
    private final GeImageLoader imageLoader;

    public TileMapReader() {
        this.imageLoader = new GeImageLoader();
    }

    public List<TileComponent> loadTileMap(String fileName, String spriteName, Scale2D scale, Integer tileSize) throws IOException {
        var stream = this.getClass().getResourceAsStream(TILEMAP_PATH + fileName);
        assert stream != null;

        var isr = new InputStreamReader(stream, StandardCharsets.UTF_8);
        var buffer = new BufferedReader(isr);

        var row = 0;
        List<TileComponent> tiles = new ArrayList<>();
        while (buffer.readLine() != null) {
            this.createTile(buffer.readLine(), tiles, scale, tileSize, row);
            row++;
        }

        var mapTexture = imageLoader.readBufferedImage(TILEMAP_PATH, spriteName);
        this.createTileTexture(tiles, tileSize, spriteName, mapTexture);

        buffer.close();
        isr.close();
        stream.close();

        return tiles;
    }

    private void createTile(String line, List<TileComponent> tiles, Scale2D scale, Integer tileSize, int row) {
        var split = line.split(COMMA_DELIMITER);
        for (int column = 0; column < split.length; column++) {
            var posX = split[column].substring(0, 1);
            var posY = split[column].substring(1, 2);

            // TODO check why has to be inverted
            int tileY = Integer.parseInt(posX) * tileSize;
            int tileX = Integer.parseInt(posY) * tileSize;

            var transform = new TransformComponent(
                    new Vector2D(column * (scale.getX() * tileSize), row * (scale.getY() * tileSize)),
                    scale,
                    0.0
            );
            var tileComponent = new TileComponent(tileX, tileY, transform);
            tiles.add(tileComponent);
        }
    }

    private void createTileTexture(List<TileComponent> tiles, Integer tileSize, String spriteName, BufferedImage mapTexture) {
        for (int k = 0; k < tiles.size(); k++) {
            var tile = tiles.get(k);
            var tileImage = mapTexture.getSubimage(
                    tile.getTileX(),
                    tile.getTileY(),
                    tileSize,
                    tileSize);

            var acceleratedImage = imageLoader.createAcceleratedImage(tileImage);
            var name = this.createTileTextureName(spriteName, tile.getTransformComponent(), k);
            tile.setTileName(name);
            AssetManager.addTileTexture(name, new TileTexture(acceleratedImage));
        }
    }

    private String createTileTextureName(String spriteName, TransformComponent transform, Integer index) {
        return spriteName + "-" + transform.getX() + "_" + transform.getY() + "-" + index;
    }
}
