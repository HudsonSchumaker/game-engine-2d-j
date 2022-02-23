package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.MovementComponent;
import com.schumakerteam.alpha.component.RigidBodyComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public final class MovementSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 0;
    private final int id;

    public MovementSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignatures();
        LogService.getInstance().engine("MovementSystem created with id: " + id);
    }

    @Override
    protected void setOnSignatures() {
        this.setOnSignature(RigidBodyComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(MovementComponent.COMPONENT_TYPE_ID);
    }

    @Override
    public void update(double deltaTime) {
        for (var entity : getSystemEntities()) {
            var movement = (MovementComponent) entity.getComponent(MovementComponent.COMPONENT_TYPE_ID);
            if (movement.isMoving()) {
                var transform = (TransformComponent) entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
                var rigidBody = (RigidBodyComponent) entity.getComponent(RigidBodyComponent.COMPONENT_TYPE_ID);

                transform.getPosition().setX(transform.getPosition().getX() + rigidBody.getVelocity().getX() * deltaTime);
                transform.getPosition().setY(transform.getPosition().getY() + rigidBody.getVelocity().getY() * deltaTime);
            }
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

    @Override
    public String toString() {
        return "MovementSystem{" + "id=" + id + '}';
    }
}
