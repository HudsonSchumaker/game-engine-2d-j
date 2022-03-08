package com.schumakerteam.alpha.component;

import com.schumakerteam.alpha.core.impl.AssetTextureManager;
import com.schumakerteam.alpha.gfx.Image2BufferedImageMapper;
import com.schumakerteam.alpha.io.GeImageLoader;
import com.schumakerteam.alpha.log.LogService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hudson Schumaker
 */
public final class SpriteSheetComponent extends SpriteComponent {

    private final int frameWidth;
    private final int frameHeight;
    private final int numberFrames;
    private final List<String> frameNames = new ArrayList<>();

    public SpriteSheetComponent(int width, int height, int z, int frameWidth, int frameHeight, int numberFrames, String spriteName) {
        super(width, height, z, spriteName);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numberFrames = numberFrames;
        this.loadFrames();
        LogService.getInstance().engine("SpriteSheetComponent created with id: " + getId());
    }

    private void loadFrames() {
        // TODO load from disk and not store on memory the big image just the frames
        var spriteSheetImage = AssetTextureManager.getTexture(this.getSpriteName()).getTexture();
        var spriteSheetBufferedImage = new Image2BufferedImageMapper().from(spriteSheetImage);
        var imageLoader = new GeImageLoader();

        for (int k = 0; k < numberFrames; k++) {
            var frameImage = spriteSheetBufferedImage.getSubimage(
                    k * frameWidth,
                    0,
                    frameWidth,
                    frameHeight);

            var acceleratedImage = imageLoader.createAcceleratedImage(frameImage);
            var name = this.createFramesTexturesNames(getSpriteName(), k);
            AssetTextureManager.addTexture(name, acceleratedImage);
            frameNames.add(name);
        }
    }

    private String createFramesTexturesNames(String spriteName, int index) {
        return spriteName + "-" + index;
    }

    public void setCurrentFrame(int index) {
        setSpriteName(frameNames.get(index));
    }

    public int getFrameWidth() {
        return this.frameWidth;
    }

    public int getFrameHeight() {
        return this.frameHeight;
    }

    @Override
    public String toString() {
        return "SpriteSheetComponent{" +
                "frameWidth=" + frameWidth +
                ", frameHeight=" + frameHeight +
                ", numberFrames=" + numberFrames +
                ", frameNames=" + frameNames +
                '}';
    }
}
