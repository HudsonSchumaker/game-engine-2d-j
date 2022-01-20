package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class SpriteComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 2;
    private final int id;

    public int w;
    public int h;

    public SpriteComponent(int w, int h) {
        this.w = w;
        this.h = h;
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
