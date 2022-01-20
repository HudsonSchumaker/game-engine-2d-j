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

    @Override
    public void update() {
        for (var entity : getSystemEntities()) {
            var transform = (TransformComponent)entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
            var rigidbody = (RigidBodyComponent)entity.getComponent(RigidBodyComponent.COMPONENT_TYPE_ID);

            transform.getPosition().setX(transform.getPosition().getX() + rigidbody.getVelocity().getX());
            transform.getPosition().setY(transform.getPosition().getY() + rigidbody.getVelocity().getY());

            LogService.getInstance().info("Entity id: " + entity.getId() +
                    " x : " +
                    transform.getPosition().getX()
                    + " y : "+
                    transform.getPosition().getY());
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
