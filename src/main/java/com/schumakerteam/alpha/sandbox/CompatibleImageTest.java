package com.schumakerteam.alpha.sandbox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CompatibleImageTest {
    public static void main(String ...args) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration config = device.getDefaultConfiguration();
        BufferedImage buffy = config.createCompatibleImage(32, 32, Transparency.TRANSLUCENT);
        Graphics g = buffy.getGraphics();



        GraphicsConfiguration gfx_config = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();
        var z = gfx_config.getColorModel();


        var x = 0;
    }
}
