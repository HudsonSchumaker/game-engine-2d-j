package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.RigidBodyComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public final class MovementSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 0;
    private final int id;

    public MovementSystem () {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignature(RigidBodyComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("MovementSystem created with id: " + id);
    }

    public void update() {
        for (var entity : getSystemEntities()) {
            LogService.getInstance().info("loop");
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getTypeId() {
        return SYSTEM_TYPE_ID;
    }
}
