package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

public class AnimationComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 4;
    private final int id;

    private final int numberFrames;
    private int currentFrame;
    private int animationSpeed;
    private long startTime;
    private final boolean loop;

    public AnimationComponent(int numberFrames, int currentFrame, int animationSpeed, boolean loop) {
        this.id = Registry.getInstance().getComponentId();
        this.numberFrames = numberFrames;
        this.currentFrame = currentFrame;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
        LogService.getInstance().engine("AnimationComponent created with id: " + id);
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {

    }

    public int getNumberFrames() {
        return numberFrames;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public boolean isLoop() {
        return loop;
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
