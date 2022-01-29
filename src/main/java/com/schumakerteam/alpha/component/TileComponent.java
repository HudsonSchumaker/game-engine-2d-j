package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;

public class TileComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 2;
    private final int id;

    private final int tileSize;
    private final int tileX;
    private final int tileY;

    public int getTileSize() {
        return tileSize;
    }

    public TileComponent(int tileSize, int tileX, int tileY) {
        this.tileSize = tileSize;
        this.tileX = tileX;
        this.tileY = tileY;
        this.id = Registry.getInstance().getComponentId();
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
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
