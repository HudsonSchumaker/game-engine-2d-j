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

    public BufferedImage readImageFromDisk(String fileName) {
        return readBufferImageFromDisk(IMAGES_PATH, fileName);
    }

    public BufferedImage readTileMapTexture(String fileName) {
        return readBufferImageFromDisk(TILEMAP_PATH, fileName);
    }

    private BufferedImage readBufferImageFromDisk(String path, String fileName) {
        try {
            return this.toCompatibleImageV2(ImageIO.read(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(path + fileName))));
        } catch (IOException ignore) {
            return null;
        }
    }

    public BufferedImage readFromWeb(String url) {
        try {
            var www = new URL(url);
            var rawImage = ImageIO.read(www);
            return this.toCompatibleImageV2(new Image2BufferedImageMapper().from(rawImage));
        } catch (IOException ignore) {
            return null;
        }
    }

    private BufferedImage toCompatibleImage(BufferedImage image) {
        GraphicsConfiguration gfxConfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        if (image.getColorModel().equals(gfxConfig.getColorModel())) {
            return image;
        }

        BufferedImage newImage = gfxConfig.createCompatibleImage(
                image.getWidth(),
                image.getHeight(),
                image.getTransparency());

        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        newImage.setAccelerationPriority(1.0f);
        // return the new optimized image
        return newImage;
    }

    private BufferedImage toCompatibleImageV2(BufferedImage image) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();

        var convertedImage = gc.createCompatibleImage(
                image.getWidth(),
                image.getHeight(),
                image.getTransparency());

        Graphics2D g2d = convertedImage.createGraphics();
        g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        g2d.dispose();

        convertedImage.setAccelerationPriority(1.0f);
        return convertedImage;
    }
}
