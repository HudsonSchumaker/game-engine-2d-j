package com.schumakerteam.alpha.io;

import com.schumakerteam.alpha.log.LogService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;

import static com.schumakerteam.alpha.common.Commons.IMAGES_PATH;
import static com.schumakerteam.alpha.common.Commons.TILEMAP_PATH;

/**
 * A class to be used for load images from disk. Is valid to notice that only java.awt.Image is an accelerated image.
 *
 * @see java.awt.Image
 */
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

    public BufferedImage readFromWeb(String url) {
        try {
            var www = new URL(url);
            return ImageIO.read(www);
        } catch (IOException ignore) {
            LogService.getInstance().error("Could not read image: " + url);
            return null;
        }
    }

    public Image readAcceleratedFromWeb(String url) {
        var rawImage = readFromWeb(url);
        return rawImage == null ? null : this.createAcceleratedImage(rawImage);
    }

    public Image createAcceleratedImage(BufferedImage source) {
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Image image = gc.createCompatibleImage(source.getWidth(), source.getHeight(), Transparency.TRANSLUCENT);

        image.getGraphics().drawImage(source, 0, 0, null);
        image.setAccelerationPriority(1.0f);
        return image;
    }
}
