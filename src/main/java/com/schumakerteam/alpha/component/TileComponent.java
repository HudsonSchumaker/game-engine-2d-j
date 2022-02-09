package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public final class TileComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 4;
    private final int id;

    private final int tileX;
    private final int tileY;
    private String tileName;
    private final TransformComponent transform;

    public TileComponent(int tileX, int tileY, TransformComponent transform) {
        this.tileX = tileX;
        this.tileY = tileY;
        this.transform = transform;
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("TileComponent created with id: " + id);
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileName(String name) {
        this.tileName = name;
    }

    public String getTileName() {
        return this.tileName;
    }

    public TransformComponent getTransformComponent() {
        return this.transform;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }

    @Override
    public String toString() {
        return "TileComponent{" + "id=" + id + ", tileX=" + tileX + ", tileY=" + tileY + ", transform=" + transform + '}';
    }
}
