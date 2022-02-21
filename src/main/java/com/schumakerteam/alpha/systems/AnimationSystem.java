package com.schumakerteam.alpha.systems;

import com.schumakerteam.alpha.component.AnimationComponent;
import com.schumakerteam.alpha.component.SpriteComponent;
import com.schumakerteam.alpha.component.SpriteSheetComponent;
import com.schumakerteam.alpha.ecs.impl.BasicSystem;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public final class AnimationSystem extends BasicSystem {

    public static final int SYSTEM_TYPE_ID = 3;
    private final int id;

    public AnimationSystem() {
        this.id = Registry.getInstance().getSystemId();
        this.setOnSignatures();
        LogService.getInstance().engine("AnimationSystem created with id: " + id);
    }

    @Override
    protected void setOnSignatures() {
        this.setOnSignature(SpriteComponent.COMPONENT_TYPE_ID);
        this.setOnSignature(AnimationComponent.COMPONENT_TYPE_ID);
    }

    @Override
    public void update() {
        for (var entity : getSystemEntities()) {
            var sprite = (SpriteSheetComponent) entity.getComponent(SpriteComponent.COMPONENT_TYPE_ID);
            var animation = (AnimationComponent) entity.getComponent(AnimationComponent.COMPONENT_TYPE_ID);

            long currentFrame = (System.currentTimeMillis() - animation.getStartTime())
                    * animation.getAnimationSpeed() / 1000 % animation.getNumberFrames();

            animation.setCurrentFrame((int)currentFrame);
            sprite.setCurrentFrame(animation.getCurrentFrame());
        }
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
