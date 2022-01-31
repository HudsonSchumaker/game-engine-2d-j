package com.schumakerteam.alpha.io;

import com.schumakerteam.alpha.gfx.Image2BufferedImageMapper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static com.schumakerteam.alpha.common.Commons.IMAGES_PATH;
import static com.schumakerteam.alpha.common.Commons.TILEMAP_PATH;

public final class GeImageLoader {

    @Deprecated
    public Image readFromDisk(String fileName) {
        return Toolkit.getDefaultToolkit()
                .getImage(this.getClass()
                        .getResource(IMAGES_PATH + fileName));
    }

    public Image readImageFromDisk(String fileName) {
        return readBufferImageFromDisk(IMAGES_PATH, fileName);
    }

    public Image readTileMapTexture(String fileName) {
        return readBufferImageFromDisk(TILEMAP_PATH, fileName);
    }

    private Image readBufferImageFromDisk(String path, String fileName) {
        try {
            return this.createAcceleratedImage(ImageIO.read(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(path + fileName))));
        } catch (IOException ignore) {
            return null;
        }
    }

    public Image readFromWeb(String url) {
        try {
            var www = new URL(url);
            var rawImage = ImageIO.read(www);
            return this.createAcceleratedImage(new Image2BufferedImageMapper().from(rawImage));
        } catch (IOException ignore) {
            return null;
        }
    }

    private Image createAcceleratedImage(BufferedImage source) {
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Image image = gc.createCompatibleImage(source.getWidth(), source.getHeight(), Transparency.TRANSLUCENT);

        image.getGraphics().drawImage(source,0,0,null);
        return image;
    }


}
