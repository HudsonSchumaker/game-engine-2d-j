package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.common.ComponentIdMap;
import com.schumakerteam.alpha.ecs.impl.Component;
import com.schumakerteam.alpha.ecs.impl.Registry;

public class InputComponent extends Component {

    public static final int COMPONENT_TYPE_ID = ComponentIdMap.getTypeId(InputComponent.class.getName());
    private final int id;

    public InputComponent() {
        this.id = Registry.getInstance().getComponentId();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }
    // TODO toString
}
