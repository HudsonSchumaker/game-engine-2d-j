package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.RigidBodyComponent;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.TransformComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

import java.awt.*;

public class RenderSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 1;
    private final int id;
    private Graphics2D g;

    public RenderSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignature(SpriteComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(TransformComponent.COMPONENT_TYPE_ID);
        LogService.getInstance().engine("RenderSystem created with id: " + id);
    }

    @Override
    public void update() {
        for (var entity : getSystemEntities()) {
            var transform = (TransformComponent)entity.getComponent(TransformComponent.COMPONENT_TYPE_ID);
            var sprite = (SpriteComponent)entity.getComponent(SpriteComponent.COMPONENT_TYPE_ID);

            g.draw(new Rectangle.Double(
                    transform.getPosition().getX(),
                    transform.getPosition().getY(),
                    sprite.w,
                    sprite.h)
            );
        }
    }

    // TODO credo!!!
    public void setGraphics2D(Graphics2D g) {
        this.g = g;
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
