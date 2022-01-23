package com.schumakerteam.alpha.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
}
