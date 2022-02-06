package com.schumakerteam.alpha.gfx;

import com.schumakerteam.alpha.common.Mapper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Image2BufferedImageMapper implements Mapper<Image, BufferedImage> {

    @Override
    public BufferedImage from(Image source) {
        BufferedImage target = new BufferedImage(source.getWidth(null), source.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics g = target.createGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return target;
    }
}
