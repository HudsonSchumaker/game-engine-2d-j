package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.AnimationComponent;
import com.schumakerteam.alpha.component.BoxColliderComponent;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class CollisionSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 3;
    private final int id;

    public CollisionSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(BoxColliderComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("CollisionSystem created with id: " + id);
    }

    public void update(double deltaTime) {
        this.update();
    }

    @Override
    protected void update() {

        for (int i = 0; i < getSystemEntities().size(); i++) {
            var aEntity = getSystemEntities().get(i);
            var aTransform = (TransformComponent) aEntity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
            var aCollider = (BoxColliderComponent) aEntity.getComponent(BoxColliderComponent.COMPONENT_TYPE_ID);

            for (int j = i; j < getSystemEntities().size(); j++) {
                var bEntity = getSystemEntities().get(j);
                var bTransform = (TransformComponent) bEntity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
                var bCollider = (BoxColliderComponent) bEntity.getComponent(BoxColliderComponent.COMPONENT_TYPE_ID);
            }
        }
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
