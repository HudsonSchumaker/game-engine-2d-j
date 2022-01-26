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
            return ImageIO.read(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream(path + fileName)));
        } catch (IOException ignore) {
            return null;
        }
    }

    public BufferedImage readFromWeb(String url) {
        try {
            var www = new URL(url);
            var image = ImageIO.read(www);
            return new Image2BufferedImageMapper().from(image);
        } catch (IOException ignore) {
            return null;
        }
    }
}
