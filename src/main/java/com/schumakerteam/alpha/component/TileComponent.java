package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;

public class TileComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 2;
    private final int id;

    int tileSize = 32;
    double tileScale = 2.0;
    int mapNumCols = 25;
    int mapNumRows = 20;

    public TileComponent() {
        this.id = Registry.getInstance().getComponentId();
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
