package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.geometry.Vector2D;
import com.schumakerteam.alpha.log.LogService;

public class BoxColliderComponent {

    public static final int COMPONENT_TYPE_ID = 4;
    private final int id;

    private int width;
    private int height;
    private Vector2D offset;


    public BoxColliderComponent() {
        this(0, 0, Vector2D.Zero());
    }

    public BoxColliderComponent(int width, int height, Vector2D offset) {
        this.width = width;
        this.height = height;
        this.offset = offset;
        this.id = Registry.getInstance().getComponentId();
        LogService.getInstance().engine("BoxColliderComponent created with id: " + id);
    }
}
