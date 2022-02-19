package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.BoxColliderComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Entity;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.events.EventBus;
import com.schumakerteam.alpha.events.EventType;
import com.schumakerteam.alpha.events.OnCollisionEvent;
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

    public void update(double ignore) {
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

                if (this.collide(aEntity, bEntity)) {
                    EventBus.getInstance().notify(EventType.ON_COLLISION_EVENT, new OnCollisionEvent(aEntity, bEntity));
                }
            }
        }
    }

    // TODO refactoring
    private boolean collide(Entity aEntity, Entity bEntity) {
        var aTransform = (TransformComponent) aEntity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
        var aCollider = (BoxColliderComponent) aEntity.getComponent(BoxColliderComponent.COMPONENT_TYPE_ID);

        var bTransform = (TransformComponent) bEntity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
        var bCollider = (BoxColliderComponent) bEntity.getComponent(BoxColliderComponent.COMPONENT_TYPE_ID);

        var pwA = (int) aTransform.getX() + aCollider.getWidth();
        var pwB = (int) bTransform.getX() + bCollider.getWidth();

        var phA = (int) aTransform.getY() + aCollider.getHeight();
        var phB = (int) bTransform.getY() + bCollider.getHeight();

        var aX = aTransform.getX() + aCollider.getOffset().getX();
        var aY = aTransform.getY() + aCollider.getOffset().getY();
        var bX = bTransform.getX() + bCollider.getOffset().getX();
        var bY = bTransform.getY() + bCollider.getOffset().getY();

        return pwA > bX && aX < pwB && phA > bY && aY < phB;
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
