package com.schumakerteam.alpha.io;

import com.schumakerteam.alpha.gfx.Image2BufferedImageMapper;
import com.schumakerteam.alpha.log.LogService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static com.schumakerteam.alpha.common.Commons.IMAGES_PATH;
import static com.schumakerteam.alpha.common.Commons.TILEMAP_PATH;

public final class GeImageLoader {

    public Image readImageFromDisk(String fileName) {
        return readImageFromDisk(IMAGES_PATH, fileName);
    }

    public Image readTileMapTexture(String fileName) {
        return readImageFromDisk(TILEMAP_PATH, fileName);
    }

    private Image readImageFromDisk(String path, String fileName) {
        try {
            return this.createAcceleratedImage(ImageIO.read(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(path + fileName))));
        } catch (IOException ignore) {
            LogService.getInstance().error("Could not read image: " + fileName);
            return null;
        }
    }

    public Image readFromDisk(String path, String fileName) {
        return Toolkit.getDefaultToolkit()
                .getImage(this.getClass()
                        .getResource(path + fileName));
    }

    public BufferedImage readBufferedImage(String path, String fileName) {
        return readBufferedImage(path + fileName);
    }

    public BufferedImage readBufferedImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(path)));
        } catch (IOException ignore) {
            LogService.getInstance().error("Could not read image: " + path);
            return null;
        }
    }

    public Image readFromWeb(String url) {
        try {
            var www = new URL(url);
            var rawImage = ImageIO.read(www);
            return this.createAcceleratedImage(new Image2BufferedImageMapper().from(rawImage));
        } catch (IOException ignore) {
            LogService.getInstance().error("Could not read image: " + url);
            return null;
        }
    }

    public Image createAcceleratedImage(BufferedImage source) {
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Image image = gc.createCompatibleImage(source.getWidth(), source.getHeight(), Transparency.TRANSLUCENT);

        image.getGraphics().drawImage(source, 0, 0, null);
        image.setAccelerationPriority(1.0f);
        return image;
    }
}
