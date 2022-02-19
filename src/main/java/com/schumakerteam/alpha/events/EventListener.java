package com.schumakerteam.alpha.events;

public interface EventListener {
    void notifyEvent(EventType eventType, Event<?> event);
}
