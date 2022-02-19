package com.schumakerteam.alpha.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EventBus {
    private static final EventBus INSTANCE = new EventBus();
    private static final Map<EventType, List<EventListener>> LISTENERS = new HashMap<>();

    static {
        for (var operation : EventType.values()) {
            LISTENERS.put(operation, new ArrayList<>());
        }
    }

    private EventBus() {
    }

    public static EventBus getInstance() {
        return INSTANCE;
    }

    public void subscribe(EventType eventType, EventListener listener) {
        List<EventListener> users = LISTENERS.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(EventType eventType, EventListener listener) {
        List<EventListener> listeners = LISTENERS.get(eventType);
        listeners.remove(listener);
    }

    public void publish(EventType eventType, Event<?> event) {
        List<EventListener> listeners = LISTENERS.get(eventType);
        for (var listener : listeners) {
            listener.notifyEvent(eventType, event);
        }
    }
}
