package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.AnimationComponent;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class CollisionSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 3;
    private final int id;

    public CollisionSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignature(SpriteComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("CollisionSystem created with id: " + id);
    }

    @Override
    protected void update() {

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
