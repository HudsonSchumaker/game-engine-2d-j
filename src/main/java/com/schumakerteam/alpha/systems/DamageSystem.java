package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.events.*;
import com.schumakerteam.alpha.log.LogService;

public class DamageSystem extends BasicSystem implements EventListener {

    public static final int SYSTEM_TYPE_ID = 5;
    private final int id;

    public DamageSystem() {
        this.id = Registry.getInstance().getSystemId();
        EventBus.getInstance().subscribe(EventType.ON_COLLISION_EVENT, this);
    }

    @Override
    protected void update() {
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
    public void update(EventType eventType, Event<?> event) {
        var onCollisionEvent = (OnCollisionEvent) event;
        LogService.getInstance().warning(onCollisionEvent.getA().toString());
    }
}
