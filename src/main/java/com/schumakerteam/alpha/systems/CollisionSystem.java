package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.BoxColliderComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Entity;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class CollisionSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 4;
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
            for (int j = i; j < getSystemEntities().size(); j++) {
                var aEntity = getSystemEntities().get(i);
                var bEntity = getSystemEntities().get(j);

                if (aEntity.getId() == bEntity.getId()) {
                    continue;
                }

                var res = collide(aEntity, bEntity);
            }
        }
    }

    private boolean collide(Entity aEntity, Entity bEntity) {
        var aTransform = (TransformComponent) aEntity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
        var aCollider = (BoxColliderComponent) aEntity.getComponent(BoxColliderComponent.COMPONENT_TYPE_ID);

        var bTransform = (TransformComponent) bEntity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
        var bCollider = (BoxColliderComponent) bEntity.getComponent(BoxColliderComponent.COMPONENT_TYPE_ID);

        var pwA = (int) aTransform.getX() + aCollider.getWidth();
        var pwB = (int) bTransform.getX() + bCollider.getWidth();

        var phA = (int) aTransform.getY() + aCollider.getHeight();
        var phB = (int) bTransform.getY() + bCollider.getHeight();


        if (pwA > bTransform.getX() && aTransform.getX() < pwB && phA > bTransform.getY() && aTransform.getY() < phB) {
            return true;
        }

        return false;
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
