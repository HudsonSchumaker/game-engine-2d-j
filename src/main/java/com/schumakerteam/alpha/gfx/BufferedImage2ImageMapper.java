package com.schumakerteam.alpha.gfx;

import com.schumakerteam.alpha.common.Mapper;
import com.schumakerteam.alpha.io.GeImageLoader;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class BufferedImage2ImageMapper implements Mapper<BufferedImage, Image> {

    @Override
    public Image from(BufferedImage source) {
        return new GeImageLoader().createAcceleratedImage(source);
    }
}
