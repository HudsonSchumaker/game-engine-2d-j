package com.schumakerteam.alpha.events;

public class OnKeyPressedEvent implements Event<OnCollisionEvent> {

    private final int keyCode;

    public OnKeyPressedEvent(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return this.keyCode;
    }
}
