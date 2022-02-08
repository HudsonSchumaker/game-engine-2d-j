package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.AnimationComponent;
import com.schumakerteam.alpha.component.RigidBodyComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;

public class AnimationSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 2;
    private final int id;
    private Graphics2D render;

    public AnimationSystem() {
        this.id = Registry.getInstance().getSystemId();
       // TODO set necessary components signatures.
        this.setOnSignature(AnimationComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("AnimationSystem created with id: " + id);
    }

    public void update(Graphics2D g) {
        this.render = g;
        this.update();
    }

    @Override
    protected void update() {
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
