package com.schumakerteam.alpha.io;

import com.schumakerteam.alpha.gfx.Image2BufferedImageMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public final class GeImageLoader {

    public Image readFromDisk(String fileName) {
        return Toolkit.getDefaultToolkit()
                .getImage(this.getClass()
                        .getResource("/resources/images/" + fileName));
    }

    public BufferedImage readBufferImageFromDisk(String fileName) {
        try {
            return ImageIO.read(Objects.requireNonNull(this.getClass()
                    .getResourceAsStream("/resources/images/" + fileName)));
        } catch (IOException ignore) {
            return null;
        }
    }

    public BufferedImage readFromWeb(String url) {
        URL www = null;
        try {
            www = new URL(url);
            Image image = ImageIO.read(www);

            var mapper = new Image2BufferedImageMap();
            return mapper.from(image);
        } catch (IOException ignore) {
        }
        return null;
    }
}
