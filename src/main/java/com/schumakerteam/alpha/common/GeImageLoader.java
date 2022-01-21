package com.schumakerteam.alpha.common;

import java.awt.*;

public final class GeImageLoader {
    public Image readFromDisk(String fileName) {
        return Toolkit.getDefaultToolkit()
                .getImage(this.getClass()
                        .getResource("/resources/images/" + fileName));
    }
}
