package com.schumakerteam.alpha.sfx;

public abstract class GeAudioClip {
    private final boolean isLoop;

    protected GeAudioClip(boolean isLoop) {
        this.isLoop = isLoop;
    }

    public abstract void stop();

    protected boolean isLoop() {
        return this.isLoop;
    }
}
