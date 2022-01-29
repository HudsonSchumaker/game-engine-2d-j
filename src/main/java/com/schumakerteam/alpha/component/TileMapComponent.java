package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.io.TileMapReader;
import com.schumakerteam.alpha.log.LogService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileMapComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 3;
    private final int id;

    private final String spriteName;
    private final String mapFileName;
    private final int tileSize;
    private final Vector2D scale;
    private List<TileComponent> tiles = new ArrayList<>();

    public TileMapComponent(String spriteName, String mapFileName, int tileSize) {
        this(spriteName, mapFileName, Vector2D.Scale(), tileSize);
    }

    public TileMapComponent(String spriteName, String mapFileName, Vector2D scale, int tileSize) {
        this.spriteName = spriteName;
        this.mapFileName = mapFileName;
        this.scale = scale;
        this.tileSize = tileSize;
        this.id = Registry.getInstance().getComponentId();
        this.load();
        LogService.getInstance().engine("TileMapComponent created with id: " + id);
    }

    private void load() {
        try {
            var tileMapReader = new TileMapReader();
            this.tiles = tileMapReader.loadTileMap(mapFileName, scale, tileSize);
        } catch (IOException ignore) {}
    }

    public void addTile(TileComponent tile) {
        this.tiles.add(tile);
    }

    public List<TileComponent> getTiles() {
        return this.tiles;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public String getMapFileName() {
        return mapFileName;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Vector2D getScale() {
        return scale;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }
}
