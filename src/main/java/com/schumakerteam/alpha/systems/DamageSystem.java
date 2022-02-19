package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Entity;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.events.EventBus;
import com.schumakerteam.alpha.events.OnCollisionEventListener;
import com.schumakerteam.alpha.log.LogService;

public class DamageSystem extends BasicSystem implements OnCollisionEventListener {

    public static final int SYSTEM_TYPE_ID = 5;
    private final int id;

    public DamageSystem() {
        this.id = Registry.getInstance().getSystemId();
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
    public void onCollisionEvent(Entity a, Entity b) {
        LogService.getInstance().info("received event");
    }
}
