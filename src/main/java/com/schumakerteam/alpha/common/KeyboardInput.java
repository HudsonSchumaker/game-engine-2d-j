package com.schumakerteam.alpha.common;

import com.schumakerteam.alpha.events.EventBus;
import com.schumakerteam.alpha.events.EventType;
import com.schumakerteam.alpha.events.OnKeyPressedEvent;

import java.awt.event.*;

public class KeyboardInput implements KeyListener {

    private final boolean[] keys;
    private int[] polled;

    public KeyboardInput() {
        keys = new boolean[256];
        polled = new int[256];
    }

    public boolean keyDown(int keyCode) {
        return polled[keyCode] > 0;
    }

    public boolean keyDownOnce(int keyCode) {
        return polled[keyCode] == 1;
    }

    public synchronized void poll() {
        for (int i = 0; i < keys.length; ++i) {
            if (keys[i]) {
                polled[i]++;
            } else {
                polled[i] = 0;
            }
        }
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true;
            EventBus.getInstance().publish(EventType.ON_KEY_PRESSED, new OnKeyPressedEvent(keyCode));
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    @Override
    public synchronized void keyTyped(KeyEvent ignore) {
    }
}
