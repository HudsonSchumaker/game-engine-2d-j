package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.common.ComponentIdMap;
import com.schumakerteam.alpha.ecs.impl.Component;
import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

/**
 * @author Hudson Schumaker
 * Schumaker Labs
 */
public final class AnimationComponent extends Component {

    public static final int COMPONENT_TYPE_ID = ComponentIdMap.getTypeId(AnimationComponent.class.getName());
    private final int id;

    private final int numberFrames;
    private int currentFrame;
    private int animationSpeed;
    private final long startTime;
    private final boolean loop;
    private boolean isPlaying;

    public AnimationComponent(int animationSpeed) {
        this(1, 0, animationSpeed, true);
    }

    public AnimationComponent(int numberFrames, int currentFrame, int animationSpeed, boolean loop) {
        this.id = Registry.getInstance().getComponentId();
        this.numberFrames = numberFrames;
        this.currentFrame = currentFrame;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
        this.startTime = System.currentTimeMillis();
        LogService.getInstance().engine("AnimationComponent created with id: " + id);
    }

    public void start() {
        this.isPlaying = true;
    }

    public void stop() {
        this.isPlaying = false;
    }

    public int getNumberFrames() {
        return this.numberFrames;
    }

    public int getCurrentFrame() {
        return this.currentFrame;
    }

    public int getAnimationSpeed() {
        return this.animationSpeed;
    }

    public boolean isLoop() {
        return this.loop;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getTypeId() {
        return COMPONENT_TYPE_ID;
    }

    @Override
    public String toString() {
        return "AnimationComponent{" +
                "id=" + id +
                ", numberFrames=" + numberFrames +
                ", currentFrame=" + currentFrame +
                ", animationSpeed=" + animationSpeed +
                ", loop=" + loop +
                '}';
    }
}
