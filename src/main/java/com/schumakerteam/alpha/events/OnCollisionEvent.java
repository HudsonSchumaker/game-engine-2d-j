package com.schumakerteam.alpha.events;

import com.schumakerteam.alpha.ecs.impl.Entity;

public class OnCollisionEvent implements Event<OnCollisionEvent> {

    private final Entity a;
    private final Entity b;

    public OnCollisionEvent(Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }

    public Entity getA() {
        return a;
    }

    public Entity getB() {
        return b;
    }
}
