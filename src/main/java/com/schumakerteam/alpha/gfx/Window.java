package com.schumakerteam.alpha.gfx;

import javax.swing.*;
import java.awt.*;

public final class Window extends JFrame {

    private JPanel panel;

    public Window(Canvas canvas) {
        this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        panel = (JPanel) this.getContentPane();
        panel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
        panel.setLayout(null);
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
