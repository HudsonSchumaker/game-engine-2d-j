package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class BoxColliderComponent {

    public static final int COMPONENT_TYPE_ID = 4;
    private final int id;

    public BoxColliderComponent() {
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("BoxColliderComponent created with id: " + id);
    }
}
