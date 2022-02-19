package com.schumakerteam.alpha.events;

public interface EventListener {
    void update(EventType eventType, Event<?> event);
}
