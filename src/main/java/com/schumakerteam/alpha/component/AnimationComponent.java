package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.ecs.impl.Registry;
import com.schumakerteam.alpha.log.LogService;

import java.util.ArrayList;
import java.util.List;

public class AnimationComponent extends Component {

    public static final int COMPONENT_TYPE_ID = 4;
    private final int id;

    private final int numberFrames;
    private int currentFrame;
    private int animationSpeed;
    private final long startTime;
    private final boolean loop;
    private List<String> frameNames;
    private final SpriteComponent spriteComponent;

    public AnimationComponent(int animationSpeed, SpriteComponent spriteComponent) {
        this(1, 1, animationSpeed, true, spriteComponent);
    }

    public AnimationComponent(int numberFrames, int currentFrame, int animationSpeed, boolean loop, SpriteComponent spriteComponent) {
        this.id = Registry.getInstance().getComponentId();
        this.numberFrames = numberFrames;
        this.currentFrame = currentFrame;
        this.animationSpeed = animationSpeed;
        this.loop = loop;
        this.startTime = System.currentTimeMillis();
        this.frameNames = new ArrayList<>();
        this.spriteComponent = spriteComponent;
        this.loadFrames();
        LogService.getInstance().engine("AnimationComponent created with id: " + id);
    }

    private void loadFrames() {
        var spriteName = spriteComponent.getSpriteName();

    }

    public void start() {

    }

    public void stop() {

    }

    public String getCurrentFrameName(int index) {
        return frameNames.get(index -1);
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
