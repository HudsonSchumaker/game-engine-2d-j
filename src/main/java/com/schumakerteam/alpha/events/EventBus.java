package com.schumakerteam.alpha.events;

import com.schumakerteam.alpha.ecs.impl.Entity;

public class EventBus {

    private static final EventBus INSTANCE = new EventBus();

    private OnCollisionEventListener collisionEventListener;


    private EventBus() {
    }

    public static EventBus getInstance() {
        return INSTANCE;
    }

    public void addCollisionEventListener(OnCollisionEventListener listener) {
        this.collisionEventListener = listener;
    }

    public void sendCollisionEvent(Entity a, Entity b) {

    }
}
