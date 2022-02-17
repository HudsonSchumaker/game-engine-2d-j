package com.schumakerteam.alpha.events;

import com.schumakerteam.alpha.ecs.impl.Entity;

public interface OnCollisionEventPublisher {
    void onCollisionEvent(Entity a, Entity b);
}
