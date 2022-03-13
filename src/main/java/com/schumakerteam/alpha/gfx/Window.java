package com.schumakerteam.alpha.gfx;

import com.schumakerteam.alpha.events.EventBus;
import com.schumakerteam.alpha.events.EventType;
import com.schumakerteam.alpha.events.OnWindowClosingEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Hudson Schumaker
 */
public final class Window extends JFrame {

    public Window(Scene scene) {
        this.setBounds(0, 0, scene.getWidth(), scene.getHeight());
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(scene.getWidth(), scene.getHeight()));
        panel.setIgnoreRepaint(true);
        panel.add(scene);

        this.setIgnoreRepaint(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                EventBus.getInstance().publish(EventType.ON_WINDOW_CLOSING, new OnWindowClosingEvent());
            }
        });
        //this.setUndecorated(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        scene.initialize();
    }
}
