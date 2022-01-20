package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class SpriteComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 2;
    private final int id;

    public SpriteComponent() {
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("SpriteComponent created with id: " + id);
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
