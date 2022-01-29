package com.schumakerteam.alpha.gfx;

import javax.swing.*;
import java.awt.*;

public final class Window extends JFrame {

    public Window(Canvas canvas) {
        this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
        panel.setIgnoreRepaint(true);
        panel.add(canvas);

        this.setIgnoreRepaint(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
