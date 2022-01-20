package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class RenderSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 1;
    private final int id;

    public RenderSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignature(SpriteComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("RenderSystem created with id: " + id);
    }

    @Override
    public void update() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTypeId() {
        return SYSTEM_TYPE_ID;
    }
}
