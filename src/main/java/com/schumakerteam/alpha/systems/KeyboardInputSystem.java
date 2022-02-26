package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.InputComponent;
import com.schumakerteam.alpha.component.RigidBodyComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.events.*;
import com.schumakerteam.alpha.log.LogService;

import java.awt.event.KeyEvent;

public class KeyboardInputSystem extends BasicSystem implements EventListener {

    public static final int SYSTEM_TYPE_ID = 6;
    private final int id;
    private double deltaTime;

    public KeyboardInputSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignatures();
        EventBus.getInstance().subscribe(EventType.ON_KEY_PRESSED, this);
        LogService.getInstance().engine("KeyboardInputSystem created with id: " + id);
    }

    @Override
    protected void setOnSignatures() {
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(RigidBodyComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(InputComponent.COMPONENT_TYPE_ID);
    }

    @Override
    public void update(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    @Override
    public void notifyEvent(EventType eventType, Event<?> event) {
        var onKeyPressedEvent = (OnKeyPressedEvent) event;

       // LogService.getInstance().error("deltaTime: " + deltaTime);
        if (onKeyPressedEvent.getKeyCode() == KeyEvent.VK_LEFT) {
            for (var entity : getSystemEntities()) {
                var transform = (TransformComponent) entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
                var rigidBody = (RigidBodyComponent) entity.getComponent(RigidBodyComponent.COMPONENT_TYPE_ID);

                transform.getPosition().setX(
                        transform.getPosition().getX()
                                + ((rigidBody.getVelocity().getX() * -1) * deltaTime));
            }
            return;
        }

        if (onKeyPressedEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
            for (var entity : getSystemEntities()) {
                var transform = (TransformComponent) entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
                var rigidBody = (RigidBodyComponent) entity.getComponent(RigidBodyComponent.COMPONENT_TYPE_ID);

                transform.getPosition().setX(
                        transform.getPosition().getX()
                                + ((rigidBody.getVelocity().getX() * 1) ));
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
        return "KeyboardInputSystem{" +
                "id=" + id +
                ", deltaTime=" + deltaTime +
                '}';
    }
}
