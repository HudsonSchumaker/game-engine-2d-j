package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.log.LogService;

public class SpriteSheetComponent extends SpriteComponent {

    private final int frameWidth;
    private final int frameHeight;

    public SpriteSheetComponent(int width, int height, int z, int frameWidth, int frameHeight, String spriteName) {
        super(width, height, z, spriteName);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        LogService.getInstance().engine("SpriteSheetComponent created with id: " + getId());
    }

    public int getFrameWidth() {
        return this.frameWidth;
    }

    public int getFrameHeight() {
        return this.frameHeight;
    }
}
